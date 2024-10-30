package com.example.hand_in_hand.Entities.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "volunteer_activities")
public class VolunteerActivities {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

    @Column(name="description")
    private String description;

    @Column(name="title")
    private String title;

    @Column(name="status")
    private int status;

    public VolunteerActivities(Location location, String description, String title, int status) {
        this.location = location;
        this.description = description;
        this.title = title;
        this.status = status;
    }

    public VolunteerActivities() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
