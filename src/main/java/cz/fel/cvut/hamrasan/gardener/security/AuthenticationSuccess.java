package cz.fel.cvut.hamrasan.gardener.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import cz.fel.cvut.hamrasan.gardener.security.model.LoginStatus;
import cz.fel.cvut.hamrasan.gardener.security.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Writes basic login/logout information into the response.
 */
@Service
public class AuthenticationSuccess implements AuthenticationSuccessHandler, LogoutSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccess.class);

    private final ObjectMapper mapper;

    @Autowired
    public AuthenticationSuccess(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        final String username = getUsername(authentication);
        final String SESSION_COOKIE_NAME = "JSESSIONID";
        final String SAME_SITE_ATTRIBUTE_VALUES = "SameSite=None;Secure";
        if (LOG.isTraceEnabled()) {
            LOG.trace("Successfully authenticated user {}", username);
        }
        final LoginStatus loginStatus = new LoginStatus(true, authentication.isAuthenticated(), username, null);

        if(httpServletResponse.containsHeader(HttpHeaders.SET_COOKIE)) {
            httpServletResponse.setHeader(HttpHeaders.SET_COOKIE, httpServletResponse.getHeader(HttpHeaders.SET_COOKIE) + SAME_SITE_ATTRIBUTE_VALUES);
        }
        else{
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            List<Cookie> cookieList = Arrays.asList(cookies);
            Cookie sessionCookie = cookieList.stream().filter(cookie -> SESSION_COOKIE_NAME.equals(cookie.getName())).findFirst().orElse(null);
            if (sessionCookie != null) {
                httpServletResponse.setHeader(HttpHeaders.SET_COOKIE, sessionCookie.getName() + "=" + sessionCookie.getValue() + SAME_SITE_ATTRIBUTE_VALUES);
            }
        }
        }

        mapper.writeValue(httpServletResponse.getOutputStream(), loginStatus);


    }

    private String getUsername(Authentication authentication) {
        if (authentication == null) {
            return "";
        }
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Successfully logged out user {}", getUsername(authentication));
        }
        final LoginStatus loginStatus = new LoginStatus(false, true, null, null);
        mapper.writeValue(httpServletResponse.getOutputStream(), loginStatus);
    }


}

