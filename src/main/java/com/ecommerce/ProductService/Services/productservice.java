package com.ecommerce.ProductService.Services;

import com.ecommerce.ProductService.Exceptions.ProductNotFoundException;
import com.ecommerce.ProductService.Modals.Product;

import java.util.List;

//since the class name is ProductService,
//I am creating this interface name as productservice
public interface productservice {
    Product getSingleProduct(Long ProductId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(String title,
                          double price,
                          String description,
                          String image,
                          String category);

    void deleteProduct(Long productId) throws ProductNotFoundException;
    List<Product> getSpecificCategory();
    Product updateProduct(Long productId,
                          String title,
                          double price,
                          String description,
                          String image,
                          String category) throws ProductNotFoundException;

}
