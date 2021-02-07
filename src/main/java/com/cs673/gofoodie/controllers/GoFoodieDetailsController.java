package com.cs673.gofoodie.controllers;

import com.cs673.gofoodie.models.User;
import com.cs673.gofoodie.repositories.FoodTruckRepository;
import com.cs673.gofoodie.services.CustomUserDetailsService;
import org.springframework.stereotype.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class GoFoodieDetailsController {
  @Autowired
  private CustomUserDetailsService userService;

  @Autowired
  private FoodTruckRepository foodTruckRepository;

  @RequestMapping(value = "/foodtrucks", method = RequestMethod.GET)
  public ModelAndView getFoodTrucks() {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("foodtrucks", foodTruckRepository.findAll());
    modelAndView.addObject("currentUser", user);
    modelAndView.addObject("fullName", "Welcome " + user.getEmail());
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.setViewName("foodtrucks");
    return modelAndView;
  }

  @RequestMapping("/foodtrucks/showFoodTruckDetails/{id}")
  public ModelAndView showFoodTruckDetails(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("currentUser", user);
    modelAndView.addObject("fullName", "Welcome " + user.getEmail());
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.addObject("showFoodTruckDetails", foodTruckRepository.findById(id).orElse(null));
    modelAndView.setViewName("showFoodTruckDetails");
    return modelAndView;
  }


}
