package com.cs673.gofoodie.controllers;

import com.cs673.gofoodie.models.FoodTruckDetails;
import com.cs673.gofoodie.models.Location;
import com.cs673.gofoodie.models.Reviews;
import com.cs673.gofoodie.models.User;
import com.cs673.gofoodie.repositories.FoodTruckDetailsRepository;
import com.cs673.gofoodie.repositories.FoodTruckRepository;
import com.cs673.gofoodie.services.CustomUserDetailsService;
import java.util.Optional;
import java.util.Set;
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

  @Autowired
  private FoodTruckDetailsRepository foodTruckDetailsRepository;

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
    System.out.println("showFoodTruckDetails input param : id =:"+id);
    Optional<FoodTruckDetails> fd =foodTruckDetailsRepository.findById(id);
    System.out.println("foodTruckDetailsRepository fd:"+fd.get());

    System.out.println("foodTruckDetailsRepository location:"+fd.get().getLocation().getLocationName());
    System.out.println("foodTruckDetailsRepository hours:"+fd.get().getHours());
    System.out.println("foodTruckDetailsRepository phone:"+fd.get().getPhoneNumbers());

    Location location = fd.get().getLocation();
    StringBuffer locationBuffer = new StringBuffer();
    locationBuffer.append(location.getLocationAddress()
        +","+location.getLocationCity()
        +" "+location.getLocationState()+" "+location.getLocationZip());
    Set<Reviews> reviews = fd.get().getReviews();
    StringBuffer reviewBuf = new StringBuffer();
    for (Reviews rv : reviews){
      reviewBuf.append(rv.getReviewerName() + " ( "+rv.getReviewerPoints()+" ) ");
    }
    System.out.println("foodTruckDetailsRepository rv:"+reviewBuf.toString());
    modelAndView.addObject("currentUser", user);
    modelAndView.addObject("fullName", "Welcome " + user.getEmail());
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.addObject("showFoodTruckDetails", fd.get());
    modelAndView.addObject("location", locationBuffer.toString());
    modelAndView.addObject("reviews", reviewBuf.toString());
    modelAndView.setViewName("showFoodTruckDetails");
    return modelAndView;
  }


}
