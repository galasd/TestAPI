package com.galasd.testAPI.repositories;

import com.galasd.testAPI.entities.Mapbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapboxRepository extends JpaRepository<Mapbox, Long> {
}
