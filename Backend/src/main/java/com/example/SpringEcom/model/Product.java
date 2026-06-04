package com.example.SpringEcom.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private Boolean productAvailable;
    private Integer stockQuantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
