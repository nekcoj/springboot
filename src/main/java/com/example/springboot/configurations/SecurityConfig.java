package com.example.springboot.configurations;

import com.example.springboot.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers( "/").permitAll()
        .antMatchers(HttpMethod.GET, "/api/v1/movie/*").authenticated()
        .antMatchers(HttpMethod.GET, "/api/v1/movie").permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/movie/add").authenticated()
        .and()
        .formLogin()
    ;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(myUserDetailsService)
        .passwordEncoder(myUserDetailsService.getEncoder());
  }
}
