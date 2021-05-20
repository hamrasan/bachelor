package cz.fel.cvut.hamrasan.gardener.config;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

public class SameSiteFilter extends GenericFilterBean {
    private final static Logger LOGGER = Logger.getLogger(SameSiteFilter.class.getName());

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse resp = (HttpServletResponse) response;
//        LOGGER.info(resp.getHeader("Set-Cookie"));
//        resp.setHeader("Set-Cookie", "SameSite=None; Secure");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//        LOGGER.info("Same Site Filter Logging Response :{}", res.getContentType());

        Collection<String> headers = res.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) { // there can be multiple Set-Cookie attributes
            if (firstHeader) {
                res.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s",  header, "SameSite=None"));
                LOGGER.info(String.format("Same Site Filter First Header %s; %s", header, "SameSite=None; Secure"));

                firstHeader = false;
                continue;
            }

            res.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s",  header, "SameSite=None"));
            LOGGER.info(String.format("Same Site Filter Remaining Headers %s; %s", header, "SameSite=None; Secure"));
        }
        chain.doFilter(request, response);
    }



}