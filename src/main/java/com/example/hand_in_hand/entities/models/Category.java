package com.example.hand_in_hand.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor // tüm alanları kullanarak bir yapıcı metot oluşturur
@NoArgsConstructor // parametresiz yapıcı metot oluşturur
@Getter //loombok kütüphanesi ile gelen bir anatasyon
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY // her yeni kayıtta id'yi otomatik arttırır
    )
    @Column(name = "id") // veritabanındaki sütun adı
    private Integer id;

    @NotBlank(message = "Category name is required")
    @Column(name="category_name")// veritabanındaki sütun adı
    private String categoryName;

    @Column(name="description") // veritabanındaki sütun adı
    private String description;
}
