package cz.fel.cvut.hamrasan.gardener.security;

import cz.fel.cvut.hamrasan.gardener.security.model.AuthenticationToken;
import cz.fel.cvut.hamrasan.gardener.security.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DefaultAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {

        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //loadUserByUsername is overloaded in userDetailService for finding user by email
        UserDetails ud = (UserDetails) userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        if (!passwordEncoder.matches(authentication.getCredentials().toString(), ud.getPassword())) {
            throw new BadCredentialsException("Not validated");
        }
        ud.eraseCredentials();
        return SecurityUtils.setCurrentUser(ud);
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass) ||
                AuthenticationToken.class.isAssignableFrom(aClass);
    }
}
