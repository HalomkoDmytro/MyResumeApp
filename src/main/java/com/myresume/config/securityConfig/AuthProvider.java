package com.myresume.config.securityConfig;

import com.myresume.entity.CurrentProfile;
import com.myresume.entity.Profile;
import com.myresume.service.FindProfileService;
import com.myresume.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private FindProfileService findProfileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userIdentification = authentication.getName();
        String password = (String) authentication.getCredentials();

        final Profile profile = findProfileService.findByUIDOrEmailOrPhone(userIdentification);

        if (profile != null) {
            if (!passwordEncoder.matches(password, profile.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }

            final CurrentProfile currentProfile = new CurrentProfile(profile);

            GrantedAuthority grantedAuthority = () -> Constants.USER;
            final UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(currentProfile, password, Collections.singleton(grantedAuthority));

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authenticationToken);
            return authenticationToken;
        } else {
            throw new BadCredentialsException("Username not found");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
