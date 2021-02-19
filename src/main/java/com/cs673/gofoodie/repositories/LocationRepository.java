package com.cs673.gofoodie.repositories;

import com.cs673.gofoodie.models.FoodTruckDetails;
import com.cs673.gofoodie.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
