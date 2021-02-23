package com.cs673.gofoodie.controllers;

import com.cs673.gofoodie.models.Location;
import com.cs673.gofoodie.models.User;
import com.cs673.gofoodie.repositories.FoodTruckDetailsRepository;
import com.cs673.gofoodie.repositories.FoodTruckRepository;
import com.cs673.gofoodie.repositories.LocationRepository;
import com.cs673.gofoodie.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GoFoodieSpotBookingController {
  @Autowired
  private CustomUserDetailsService userService;

  @Autowired
  private FoodTruckRepository foodTruckRepository;

  @Autowired
  private FoodTruckDetailsRepository foodTruckDetailsRepository;

  @Autowired
  private LocationRepository locationRepository;

  @RequestMapping(value = "/listAvailableSpots", method = RequestMethod.GET)
  public ModelAndView getListAvailableSpots() {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("foodtrucklocations", locationRepository.findAll());
    modelAndView.addObject("currentUser", user);
    modelAndView.addObject("fullName", "Welcome " + user.getEmail());
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.setViewName("listAvailableSpots");
    return modelAndView;
  }
  @RequestMapping(value = "/listAvailableSpots/showSpotDetails/{id}", method = RequestMethod.GET)
  public ModelAndView showSpotDetails(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("foodtrucklocation", locationRepository.findById(id).get());
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.addObject("bookingmessage", "Booking ??");
    modelAndView.setViewName("showSpotDetails");
    return modelAndView;
  }

  @RequestMapping(value = "/bookspot/{id}", method = RequestMethod.GET)
  public ModelAndView getBookSpot(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    Location lc = locationRepository.findById(id).get();
    lc.setAvailableSpots(lc.getAvailableSpots() - 1);
    locationRepository.save(lc);
    modelAndView.addObject("foodtrucklocation", locationRepository.findById(id).get());
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.setViewName("showSpotDetails");

    modelAndView.addObject("bookingmessage", "Spot booked!!!");

    modelAndView.setViewName("showSpotDetails");
    return modelAndView;
  }
}
