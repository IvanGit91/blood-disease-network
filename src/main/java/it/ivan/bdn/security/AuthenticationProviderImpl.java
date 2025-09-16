package it.ivan.bdn.security;

import it.ivan.bdn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Note the @Primary annotation, as I chose to use manual authentication instead of automatic authentication.
// If you want to use automatic authentication, remove this annotation and add @Primary to the method defined in the configuration.
@Service
@Primary
@RequiredArgsConstructor
public class AuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {
        if(userDetails.getUsername() == null || token.getCredentials() == null) {
        	throw new BadCredentialsException("Credential may not be null.");
        }

        if (!bCryptPasswordEncoder.matches(token.getCredentials().toString(), userDetails.getPassword())) {
            System.out.println("Error credenziali");
            throw new BadCredentialsException("Invalid Credentials.");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {
        return userService.loadUserByUsername(username);
    }

}