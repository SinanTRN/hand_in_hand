package com.example.hand_in_hand.Entities.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "demands")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="needy_id")
    private Integer needyId;

    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="description")
    private String description;

    public Demand() {
    }
    public Demand(Integer needyId, Integer categoryId, String description) {
        this.needyId = needyId;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
