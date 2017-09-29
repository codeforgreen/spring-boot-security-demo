package ro.mmitran.auth.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import ro.mmitran.auth.service.trottling.AuthenticationAttemptService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by student on 29.09.2017.
 */
@Component
public class PreventTrottlingFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(PreventTrottlingFilter.class);

    @Autowired
    private AuthenticationAttemptService authenticationAttemptService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/login")) {
            String authorizationHeader = request.getHeader("authorization");
            String authorizationToken = new String(Base64.decode(authorizationHeader.split(" ")[1].getBytes()));
            String username = authorizationToken.split(":")[0];
            if (!authenticationAttemptService.allowLogin(username)) {
                logger.info("Maximum number of attempts reached for username:" + username);
                response.sendRedirect("/");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
