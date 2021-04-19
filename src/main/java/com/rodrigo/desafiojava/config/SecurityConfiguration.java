package com.rodrigo.desafiojava.config;

import com.rodrigo.desafiojava.filter.JwtFilter;
import com.rodrigo.desafiojava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users/login","/users/create").permitAll()
                .antMatchers(HttpMethod.GET,  "/users/getall").authenticated()
                .antMatchers(HttpMethod.DELETE, "/uuid").authenticated()
                .antMatchers(HttpMethod.PUT, "/users/update").authenticated();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }


}