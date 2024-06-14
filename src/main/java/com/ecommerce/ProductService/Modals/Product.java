package com.ecommerce.ProductService.Modals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private Category category;
}
