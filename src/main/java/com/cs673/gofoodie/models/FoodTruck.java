package com.cs673.gofoodie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FOODTRUCK")
public class FoodTruck {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String type;

  private String distance;



  @OneToOne
  @JoinColumn(name="foodTruckId", nullable=false)
  private FoodTruck foodTruck;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDistance() {
    return distance;
  }

  public void setDistance(String distance) {
    this.distance = distance;
  }

  public FoodTruck getFoodTruck() {
    return foodTruck;
  }

  public void setFoodTruck(FoodTruck foodTruck) {
    this.foodTruck = foodTruck;
  }



}
