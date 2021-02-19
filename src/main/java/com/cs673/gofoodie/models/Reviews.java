package com.cs673.gofoodie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEWS")
public class Reviews {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String reviewerName;
  private String reviewerPoints;
  @ManyToOne
  @JoinColumn(name="foodTruckId", nullable=false)
  private FoodTruckDetails foodTruckDetails;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public String getReviewerPoints() {
    return reviewerPoints;
  }

  public void setReviewerPoints(String reviewerPoints) {
    this.reviewerPoints = reviewerPoints;
  }

  public FoodTruckDetails getFoodTruckDetails() {
    return foodTruckDetails;
  }

  public void setFoodTruckDetails(FoodTruckDetails foodTruckDetails) {
    this.foodTruckDetails = foodTruckDetails;
  }
}
