package com.galasd.testAPI.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "mapbox")
@EntityListeners(AuditingEntityListener.class)
public class Mapbox {
    // Specified columns in given mapbox table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID", unique = true, nullable = false)
    private Integer placeId;

    @Column(name = "NAME", unique = false, nullable = false)
    private String name;

    @Column(name = "LATITUDE", unique = true, nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", unique = true, nullable = false)
    private Double longitude;

    // Accessors and mutators for all fields
    public Mapbox() {
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int id) {
        this.placeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String placeName) {
        this.name = placeName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double placeLatitude) {
        this.latitude = placeLatitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double placeLongitude) {
        this.longitude = placeLongitude;
    }
}