package com.example.hand_in_hand.Entities.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="philanthropis_id")
    private Integer philanthropisId;

    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="needy_id")
    private Integer needyId;

    @Column(name="description")
    private String description;

    @Column(name="location_id")
    private Integer locationId;

    @Column(name="time")
    private String time;

    public Donation() {
    }
    public Donation(Integer philanthropisId, Integer categoryId, Integer needyId, String description, Integer locationId, String time) {
        this.philanthropisId = philanthropisId;
        this.categoryId = categoryId;
        this.needyId = needyId;
        this.description = description;
        this.locationId = locationId;
        this.time = time;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNeedyId() {
        return needyId;
    }

    public void setNeedyId(Integer needyId) {
        this.needyId = needyId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPhilanthropisId() {
        return philanthropisId;
    }

    public void setPhilanthropisId(Integer philanthropisId) {
        this.philanthropisId = philanthropisId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
