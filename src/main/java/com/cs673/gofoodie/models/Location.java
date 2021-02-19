package com.cs673.gofoodie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String locationName;
  private String locationAddress;
  private String locationCity;
  private String locationState;
  private String locationZip;

  private Integer totalSpots;
  private Integer availableSpots;

  private String locality;
  private String popularity;

  @ManyToOne
  @JoinColumn(name="foodTruckId", nullable=false)
  private FoodTruckDetails foodTruckDetails;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getLocationAddress() {
    return locationAddress;
  }

  public void setLocationAddress(String locationAddress) {
    this.locationAddress = locationAddress;
  }

  public String getLocationCity() {
    return locationCity;
  }

  public void setLocationCity(String locationCity) {
    this.locationCity = locationCity;
  }

  public String getLocationState() {
    return locationState;
  }

  public void setLocationState(String locationState) {
    this.locationState = locationState;
  }

  public String getLocationZip() {
    return locationZip;
  }

  public void setLocationZip(String locationZip) {
    this.locationZip = locationZip;
  }

  public FoodTruckDetails getFoodTruckDetails() {
    return foodTruckDetails;
  }

  public void setFoodTruckDetails(FoodTruckDetails foodTruckDetails) {
    this.foodTruckDetails = foodTruckDetails;
  }

  public Integer getTotalSpots() {
    return totalSpots;
  }

  public void setTotalSpots(Integer totalSpots) {
    this.totalSpots = totalSpots;
  }

  public Integer getAvailableSpots() {
    return availableSpots;
  }

  public void setAvailableSpots(Integer availableSpots) {
    this.availableSpots = availableSpots;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getPopularity() {
    return popularity;
  }

  public void setPopularity(String popularity) {
    this.popularity = popularity;
  }
}
