package com.georgivelev.demoapprestapi.services;

import com.georgivelev.demoapprestapi.dao.repositories.UserRepository;
import com.georgivelev.demoapprestapi.entities.models.User;
import com.georgivelev.demoapprestapi.io.in.UserAuthenticationRequestModel;
import com.georgivelev.demoapprestapi.io.out.UserAuthenticationResponseModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.georgivelev.demoapprestapi.appconfig.ApplicationConstants.NO_SUCH_USER_WITH_PROVIDED_USERNAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    public static final String SUCCESSFUL_AUTHENTICATION_FOR_USER_DEFAULT_MESSAGE = "Successful Authentication for user: %s";
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public UserAuthenticationResponseModel authenticateUser(UserAuthenticationRequestModel authenticationRequest) {

        UsernamePasswordAuthenticationToken providedAuthentication =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        log.info("Start Authenticating user {}", authenticationRequest.getEmail());
        log.debug("Start Authenticating user: {}", authenticationRequest );

        Authentication authentication = authenticationManager.authenticate(providedAuthentication);

        log.info("Successfully authenticated user {}", authenticationRequest.getEmail());
        log.debug("Successfully authenticated user: {}", authenticationRequest );

        User user = userRepository.findUserByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(NO_SUCH_USER_WITH_PROVIDED_USERNAME));

        String jwtToken = jwtService.generateToken(user);

        log.info("JwtToken was successfully generated for user: {}", user.getUsername());
        return new UserAuthenticationResponseModel(user.getUsername(),
                String.format(SUCCESSFUL_AUTHENTICATION_FOR_USER_DEFAULT_MESSAGE, user.getUsername()), "Bearer " + jwtToken);
    }
}
