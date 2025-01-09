package com.example.hand_in_hand.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="password")
    private String password;

    @Column(name="username")
    private String username;

    @ManyToMany // (bir kullanıcının birden fazla rolü olabilir) rol tablosu ile ilişkilendirme
    @JoinTable(
            name = "user_roles", // ara tablo
            joinColumns = @JoinColumn(name = "user_id"),// ara tablodaki user_id
            inverseJoinColumns = @JoinColumn(name = "role_id")// ara tablodaki role_id
    )
    private List<Role> roles;
}
