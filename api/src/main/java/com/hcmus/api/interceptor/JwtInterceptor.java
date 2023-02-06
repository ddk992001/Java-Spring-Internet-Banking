package com.hcmus.api.interceptor;

import com.hcmus.api.common.utils.DateUtils;
import com.hcmus.api.common.utils.JwtUtils;
import com.hcmus.api.common.variables.AuthEndPoint;
import com.hcmus.api.common.variables.CommonEndPoint;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.common.variables.Role;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.entity.UserAccount;
import com.hcmus.api.model.entity.UserType;
import com.hcmus.api.repository.UserAccountRepository;
import com.hcmus.api.repository.UserTypeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final String BASE_URL = "http://localhost:8080/api/v1";

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String urlPath = request.getRequestURL().toString();
        String endPointPart = urlPath.split(BASE_URL)[1];
        String httpMethod = request.getMethod();

        for (CommonEndPoint endPoint : CommonEndPoint.values()) {
            if (endPointPart.equals(endPoint.getEndPoint()) && httpMethod.equals(endPoint.getHttpMethod().name()))
                return true;
        }

        String accessToken = request.getHeader("access_token");
        String refreshToken = request.getHeader("refresh_token");

        if (accessToken.isEmpty())
            throw new GenericException(FailedOperation.UNAUTHENTICATED_USER, ExceptionType.UNAUTHENTICATED_EXCEPTION);

        String username = JwtUtils.getUsernameFromToken(accessToken);
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(username);

        if (userAccountOptional.isEmpty())
            throw new GenericException(FailedOperation.UNAUTHENTICATED_USER, ExceptionType.UNAUTHENTICATED_EXCEPTION);

        UserAccount userAccount = userAccountOptional.get();

        if (isSendingNewToken(userAccount, accessToken, refreshToken)) {
            setUpAccessTokenResponse(response, username);
            return false;
        }

        if (!isAuthRole(userAccount, endPointPart, httpMethod))
            throw new GenericException(FailedOperation.UNAUTHORIZED_USER, ExceptionType.UNAUTHORIZED_EXCEPTION);

        return true;
    }

    public boolean isSendingNewToken(UserAccount userAccount, String accessToken, String refreshToken) throws GenericException {
        if (!JwtUtils.isValidToken(accessToken)) {
            if (userAccount.getRefreshToken().equals(refreshToken) && userAccount.getLastExpiredAt() > DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now()))
                return true;
            throw new GenericException(FailedOperation.UNAUTHENTICATED_USER, ExceptionType.UNAUTHENTICATED_EXCEPTION);
        }
        else
            if (userAccount.getLastExpiredAt() <= DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now()))
                throw new GenericException(FailedOperation.UNAUTHENTICATED_USER, ExceptionType.UNAUTHENTICATED_EXCEPTION);
        return false;
    }

    public void setUpAccessTokenResponse(HttpServletResponse response, String username) throws IOException {
        String newAccessToken = JwtUtils.createAccessToken(username);
        String jsonBodyResponse = "{\"accessToken\": \"" + newAccessToken + "\"}";;
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonBodyResponse);
    }

    public boolean isAuthRole(UserAccount userAccount, String endPointPart, String httpMethod) {
        UserType userType = userTypeRepository.findById(userAccount.getUserId()).get();
        String userTypeName = userType.getUserTypeName();
        AntPathMatcher matchHandler = new AntPathMatcher();

        for (AuthEndPoint endPoint : AuthEndPoint.values()) {
            if (matchHandler.match(endPoint.getEndPoint(), endPointPart) && httpMethod.equals(endPoint.getHttpMethod().name())) {
                Role endPointRole = endPoint.getRole();
                Role userRole = Role.findRoleByName(userTypeName);
                if (endPointRole.getName().equals(Role.NO_ROLE.getName()) || userRole.getPriority() >= endPointRole.getPriority())
                    return true;
            }
        }

        return false;
    }
}
