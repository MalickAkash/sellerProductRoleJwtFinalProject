package com.example.demoSpringSellerJwt.Entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotNull
    private Integer productQuantity;
    @NotNull
    private String productName;
    @NotNull
    private Integer productPrice;
    private int productDiscount;
    @NotNull
    private int productNetPrice;

}
