package com.example.hand_in_hand.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "demands")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @ManyToOne // (bir kullanıcının birden fazla talebi olabilir) user tablosu ile ilişkilendirme
    @JoinColumn(name = "needy_id", nullable = false)
    private User needy;  // Needy'i User türünde yaparak ilişkilendirdik

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;  // Category'i Category türünde yaparak ilişkilendirdik

    @Column(name="description")
    private String description;
}
