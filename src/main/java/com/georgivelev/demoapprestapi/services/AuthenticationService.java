package com.georgivelev.demoapprestapi.services;

import com.georgivelev.demoapprestapi.io.in.UserAuthenticationRequestModel;
import com.georgivelev.demoapprestapi.io.out.UserAuthenticationResponseModel;

public interface AuthenticationService {
    UserAuthenticationResponseModel authenticateUser(UserAuthenticationRequestModel authenticationRequest);
}
