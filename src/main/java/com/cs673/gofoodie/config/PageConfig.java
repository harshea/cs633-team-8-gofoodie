package com.cs673.gofoodie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  /**
   * This is an override method to register the controllers and the views.
   * @param registry
   */
  @Override
  public  void addViewControllers( ViewControllerRegistry registry){
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/").setViewName("login");
    registry.addViewController("/foodtrucks").setViewName("foodtrucks");
  }
}
