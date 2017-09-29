package ro.mmitran.auth.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ro.mmitran.auth.service.trottling.AuthenticationAttemptService;

/**
 * Created by student on 29.09.2017.
 */
@Component
public class AuthenticationFailedListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private Logger logger = LoggerFactory.getLogger(AuthenticationFailedListener.class);

    @Autowired
    private AuthenticationAttemptService authenticationAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Authentication authentication = event.getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        authenticationAttemptService.loginFailed(username);
        logger.info("Bad credentials input for user: " + username);
    }
}
