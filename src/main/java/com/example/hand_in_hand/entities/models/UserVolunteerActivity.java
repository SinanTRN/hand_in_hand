package com.example.hand_in_hand.entities.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_volunteeractivities")
public class UserVolunteerActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "volunteerActivities_id", nullable = false)
    private VolunteerActivities volunteerActivity;

    public UserVolunteerActivity() {
    }

    public UserVolunteerActivity(User user, VolunteerActivities volunteerActivity) {
        this.user = user;
        this.volunteerActivity = volunteerActivity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VolunteerActivities getVolunteerActivity() {
        return volunteerActivity;
    }

    public void setVolunteerActivity(VolunteerActivities volunteerActivity) {
        this.volunteerActivity = volunteerActivity;
    }
}
