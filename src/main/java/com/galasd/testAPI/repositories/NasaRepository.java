package com.galasd.testAPI.repositories;

import com.galasd.testAPI.entities.Nasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasaRepository extends JpaRepository<Nasa, Long> {
}
