package com.example.vehicleownerapi.repository;

import com.example.vehicleownerapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
