package com.hcmus.api.interceptor;

import com.hcmus.api.common.utils.DateUtils;
import com.hcmus.api.common.utils.JwtUtils;
import com.hcmus.api.common.variables.CommonEndPoint;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.entity.UserAccount;
import com.hcmus.api.repository.UserAccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String urlPath = request.getRequestURL().toString();
        String httpMethod = request.getMethod();

        for (CommonEndPoint endPoint : CommonEndPoint.values()) {
            if (httpMethod.equals(endPoint.getHttpMethod().name()) && urlPath.contains(endPoint.getEndPoint()))
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
        if (!JwtUtils.isValidToken(accessToken)) {
            if (userAccount.getRefreshToken().equals(refreshToken) && userAccount.getLastExpiredAt() > DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now())) {
                String newAccessToken = JwtUtils.createAccessToken(username);
                String jsonBodyResponse = "{\"accessToken\": \"" + newAccessToken + "\"}";;
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonBodyResponse);
                return false;
            }
            else
                throw new GenericException(FailedOperation.UNAUTHENTICATED_USER, ExceptionType.UNAUTHENTICATED_EXCEPTION);
        }
        else {
            if (userAccount.getLastExpiredAt() <= DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now()))
                throw new GenericException(FailedOperation.UNAUTHENTICATED_USER, ExceptionType.UNAUTHENTICATED_EXCEPTION);
        }

        return true;
    }
}
