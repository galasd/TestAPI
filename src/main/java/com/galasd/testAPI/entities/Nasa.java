package com.galasd.testAPI.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "nasa")
@EntityListeners(AuditingEntityListener.class)
public class Nasa {
    // Specified columns in given nasa table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASTEROID_ID", unique = true, nullable = false)
    private Integer asteroidId;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "EARTH_DISTANCE", unique = false, nullable = false, length = 100)
    private Integer earthDistance;

    // Accessors and mutators for all three fields
    public Nasa() {
    }

    public int getAsteroidId() {
        return asteroidId;
    }

    public void setAsteroidId(int id) {
        this.asteroidId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEarthDistance() {
        return earthDistance;
    }

    public void setEarthDistance(int distance) {
        this.earthDistance = distance;
    }
}