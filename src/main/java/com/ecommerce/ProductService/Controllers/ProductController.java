package com.ecommerce.ProductService.Controllers;

import com.ecommerce.ProductService.Modals.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @PostMapping("/products")
    public void createProduct(){
        System.out.println("Prouct created");
    }
    @GetMapping("/product/{id}")
    public Product getProductDetails(@PathVariable("id") Long id){
        return new Product();
    }
    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return new ArrayList<>();
    }

}
