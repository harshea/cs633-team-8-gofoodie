package com.cs673.gofoodie.controllers;

import com.cs673.gofoodie.models.User;
import com.cs673.gofoodie.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

  @Autowired
  private CustomUserDetailsService userService;

  /**
   * This method creates a model and view method for the login page.
   */

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("login");
    return  modelAndView;
  }
  /**
   * This method creates a model and view method for the signup/register  page.
   */
  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public ModelAndView signup() {
    System.out.println("*********Inside signup********");
    ModelAndView modelAndView = new ModelAndView();
    User user = new User();
    modelAndView.addObject("user", user);
    modelAndView.setViewName("signup");
    return modelAndView;
  }

  /**
   * This method creates a model and view method for saving the new user
   * when form submitted from the signup page..
   */
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public ModelAndView createNewUser( User user, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    User userExists  = userService.findUserByEmail(user.getEmail());
    System.out.println("userExists="+userExists);
    if(userExists != null){
      bindingResult.rejectValue("email","email.user","There is already a user registered with the username provided");
    }
    System.out.println("bindingResult.hasErrors="+bindingResult.hasErrors());
    if(bindingResult.hasErrors()){
      System.out.println("bindingResult.hasErrors="+bindingResult.hasErrors());
      modelAndView.addObject("successMessage","There is already a user registered with the username provided");

      modelAndView.setViewName("signup");
    }else{
      userService.saveUser(user);
      modelAndView.addObject("successMessage","User has been registered successfully");
      modelAndView.addObject("user",new User());
      modelAndView.setViewName("login");
    }
    return modelAndView;
  }
}
