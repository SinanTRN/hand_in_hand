package com.example.hand_in_hand.Entities.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "philanthropist_id", nullable = false)
    private User philanthropist;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "needy_id", nullable = false)
    private User needy;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private LocalDateTime time;

    public Donation(User philanthropist, Category category, User needy, Location location, String description, LocalDateTime time) {
        this.philanthropist = philanthropist;
        this.category = category;
        this.needy = needy;
        this.location = location;
        this.description = description;
        this.time = time;
    }
    public Donation() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getPhilanthropist() {
        return philanthropist;
    }

    public void setPhilanthropist(User philanthropist) {
        this.philanthropist = philanthropist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getNeedy() {
        return needy;
    }

    public void setNeedy(User needy) {
        this.needy = needy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
