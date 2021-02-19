package com.cs673.gofoodie.models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FOODTRUCKDETAILS")
public class FoodTruckDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne(mappedBy = "foodTruckDetails", cascade = CascadeType.ALL)
  private Location location;

  private String hours;
  private String phoneNumbers;

  @OneToMany(mappedBy="foodTruckDetails")
  private Set<Reviews> reviews;

  @OneToOne(mappedBy = "foodTruck", cascade = CascadeType.ALL)
  private FoodTruck foodTruck;

  private String waitingTime;

  private String distance;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public String getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(String phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public Set<Reviews> getReviews() {
    return reviews;
  }

  public void setReviews(Set<Reviews> reviews) {
    this.reviews = reviews;
  }

  public FoodTruck getFoodTruck() {
    return foodTruck;
  }

  public void setFoodTruck(FoodTruck foodTruck) {
    this.foodTruck = foodTruck;
  }

  public String getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(String waitingTime) {
    this.waitingTime = waitingTime;
  }

  public String getDistance() {
    return distance;
  }

  public void setDistance(String distance) {
    this.distance = distance;
  }
}
