package com.cs673.gofoodie.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    System.out.println("*********Inside onAuthenticationSuccess********");
    System.out.println("request=" + request);
    // set our response to OK status
    response.setStatus(HttpServletResponse.SC_OK);
    System.out.println("request userType =" + request.getParameter("userType"));
    System.out.println("Inside onAuthenticationSuccess==");
    System.out.println("authentication==" + authentication.getPrincipal());
    User user = (User) authentication.getPrincipal();
    System.out.println("user==" + user);
    System.out.println("auth==" + authentication.getAuthorities().toString());

    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    System.out.println("userDetails usertype==" + userDetails.getUserType());

    if ("Owner".equalsIgnoreCase(userDetails.getUserType())) {
      response.sendRedirect("/listAvailableSpots");
    } else {
      response.sendRedirect("/foodtrucks");
    }

  }
}
