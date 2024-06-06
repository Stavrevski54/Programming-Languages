package com.example.vehicleownerapi.repository;

import com.example.vehicleownerapi.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
