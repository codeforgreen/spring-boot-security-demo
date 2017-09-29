package ro.mmitran.auth.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by student on 29.09.2017.
 */
@Component
public class SessionStateListener implements HttpSessionListener {
    private Logger logger = LoggerFactory.getLogger(AuthenticationFailedListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        logger.info("Created session with id:" + httpSession.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        SecurityContext securityContext = (SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
        logger.info("Destroyed session with id:" + httpSession.getId() + " for user with username:" + username + ".");
    }
}