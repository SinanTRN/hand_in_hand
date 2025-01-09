package com.example.hand_in_hand.entities.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "philanthropist_id")
    private User philanthropist;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "needy_id")
    private User needy;

    @ManyToOne // (bir bağışın bir konumu olabilir) location tablosu ile ilişkilendirme
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private String time;

    @Column(name = "status")
    private int status;
}
