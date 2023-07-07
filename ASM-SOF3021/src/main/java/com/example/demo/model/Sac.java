package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Product")
public class Sac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @Positive
    @Min(0)
    @Column(name = "price")
    private BigDecimal price;
    @NotNull(message = "Vui lòng điền số lượng")
    @PositiveOrZero
    @Min(0)
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "image")
    private String image;
    @NotBlank(message = "Vui lòng chọn loại")
    @Column(name = "category")
    private String category;
    @NotNull(message = "Vui lòng điền công suất")
    @Column(name = "power")
    private int power;
    @NotBlank(message = "Vui lòng điền màu sắc")
    @Column(name = "color")
    private String color;

}
