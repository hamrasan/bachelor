package cz.fel.cvut.hamrasan.gardener.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class SameSiteFilter extends GenericFilterBean {
    private final static Logger LOGGER = Logger.getLogger(SameSiteFilter.class.getName());

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        LOGGER.info(resp.getHeader("Set-Cookie"));
        resp.setHeader("Set-Cookie", "SameSite=None; Secure");
        chain.doFilter(request, response);
    }



}