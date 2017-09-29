package ro.mmitran.auth.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by student on 29.09.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests().antMatchers("/", "/login", "/logout").permitAll()
                .antMatchers("/hello/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login")
        .and()
        .logout().deleteCookies("remember-me")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true) ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("password").roles("USER")
                .and()
                .withUser("user2").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
}
