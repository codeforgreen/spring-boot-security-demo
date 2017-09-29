package ro.mmitran.auth.service.trottling;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 29.09.2017.
 */
@Component
public class AuthenticationAttemptService {
    private static final int MAX_LOGIN_ATTEMPT = 3;

    private Map<String, Integer> loginAttemptMap;

    @PostConstruct
    public void init() {
        loginAttemptMap = new HashMap<String, Integer>();
    }

    public void loginSuccess(final String username) {
        loginAttemptMap.remove(username);
    }

    public void loginFailed(final String username) {
        Integer attempts = loginAttemptMap.get(username);
        if (attempts == null) {
            attempts = 1;
            loginAttemptMap.put(username, attempts);
        }
        attempts++;
        loginAttemptMap.put(username, attempts);
    }

    public boolean allowLogin(final String username) {
        Integer attempts = loginAttemptMap.get(username);
        return attempts != null ? attempts <= MAX_LOGIN_ATTEMPT : true;
    }
}
