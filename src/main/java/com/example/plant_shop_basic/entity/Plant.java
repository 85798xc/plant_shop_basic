package com.example.plant_shop_basic.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "plants")
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount_in_stock")
    private BigDecimal amountInstock;

    @Column(name = "plantName")
    private String plantName;

    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmountInstock() {
        return amountInstock;
    }

    public void setAmountInstock(BigDecimal amountInstock) {
        this.amountInstock = amountInstock;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}