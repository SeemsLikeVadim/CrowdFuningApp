package com.bezkoder.spring.security.jwt.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Campaign")
public class Campaign {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
    @Column(name = "title")
    private String title;

    @Size(min = 2, max = 1000, message = "Описание должно быть от 2 до 1000 символов длиной")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Автор не должнен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    @Column(name = "author")
    private String author;

    private Double totalPrice;

    private Double currentPrice;

    private Date endDate;


    public Campaign(String title, String description, String author, Double totalPrice, Double currentPrice, Date endDate) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.currentPrice = currentPrice;
        this.totalPrice = totalPrice;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }





    public Campaign(){

    }
}
