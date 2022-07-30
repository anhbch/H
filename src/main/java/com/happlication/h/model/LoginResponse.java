package com.happlication.h.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
