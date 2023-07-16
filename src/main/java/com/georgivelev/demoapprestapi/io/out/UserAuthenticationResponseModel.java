package com.georgivelev.demoapprestapi.io.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthenticationResponseModel {
    private String username;
    private String message;
    private String jwtToken;
}
