package com.myresume.config.securityConfig;

import com.myresume.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityConfig
        extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/my-profile", "/edit", "/edit/**", "/remove")
                .hasAuthority(Constants.USER)
                .anyRequest()
                .permitAll();

        http.formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/sign-in-handler")
                .usernameParameter("uid")
                .passwordParameter("password")
                .defaultSuccessUrl("/my-profile")
                .failureUrl("/sign-in-failed");

        http.logout()
                .logoutUrl("/sign-out")
                .logoutSuccessUrl("/welcome")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .key("resume-online")
                .tokenRepository(persistentTokenRepository());

//        http.csrf().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); //todo
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }



//
//    @Bean
//    public SecurityContextHolderAwareRequestFilter awareRequestFilter() {
//        return new SecurityContextHolderAwareRequestFilter();
//    }
//
//    @Bean
//    public SecurityContextPersistenceFilter persistenceFilter() {
//        return new SecurityContextPersistenceFilter();
//    }
}

