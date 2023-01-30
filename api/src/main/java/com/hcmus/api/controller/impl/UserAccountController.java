package com.hcmus.api.controller.impl;

import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserAccountDTO;
import com.hcmus.api.service.impl.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    static class LoginForm {
        private final String username;
        private final String password;

        LoginForm(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<UserAccountDTO> login(@RequestBody LoginForm loginForm) throws GenericException {
        UserAccountDTO userAccountDTO = userAccountService.authenticateAccount(loginForm.username, loginForm.password);
        return ResponseEntity.ok(userAccountDTO);
    }
}
