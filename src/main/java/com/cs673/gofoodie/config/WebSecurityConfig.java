package com.cs673.gofoodie.config;

import com.cs673.gofoodie.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public UserDetailsService jpaUserDetails() {
    return new CustomUserDetailsService();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    System.out.println("Inside WebSEcurityConfig");
    UserDetailsService userDetailsService = jpaUserDetails();

    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    System.out.println("auth="+auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/h2/console").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/signup").permitAll()
        .antMatchers("/foodtrucks").permitAll()
        .antMatchers("/foodtrucks/**").permitAll()
        .antMatchers("/showFoodTruckDetails").permitAll()
        .antMatchers("/showFoodTruckDetails/**").permitAll()
        .antMatchers("/listAvailableSpots").permitAll()
        .antMatchers("/listAvailableSpots/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .formLogin()
          .successHandler(customizeAuthenticationSuccessHandler)
          .loginPage("/login")
          .failureUrl("/login?error=true")
           .usernameParameter("email")
           .passwordParameter("password")

        .and()
        .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
        .exceptionHandling().and().headers().frameOptions().sameOrigin();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
  }
}

