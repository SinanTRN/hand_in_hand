package com.example.hand_in_hand.entities.models;

import jakarta.persistence.*;

@Entity
@Table(name = "demands")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "needy_id", nullable = false)
    private User needy;  // Needy'i User türünde yaparak ilişkilendirdik

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;  // Category'i Category türünde yaparak ilişkilendirdik

    @Column(name="description")
    private String description;

    public Demand(User needy, Category category, String description) {
        this.needy = needy;
        this.category = category;
        this.description = description;
    }
    public Demand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getNeedy() {
        return needy;
    }

    public void setNeedy(User needy) {
        this.needy = needy;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
