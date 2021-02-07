package com.cs673.gofoodie.repositories;

import com.cs673.gofoodie.models.FoodTruck;
import com.cs673.gofoodie.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTruckRepository extends JpaRepository<FoodTruck,Long> {

}
