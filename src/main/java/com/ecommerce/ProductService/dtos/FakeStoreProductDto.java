package com.ecommerce.ProductService.dtos;

import com.ecommerce.ProductService.Modals.Category;
import com.ecommerce.ProductService.Modals.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    //This function we are writing for changing the names of the
    //3rd party api names to the required names which is requried by us
    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;
    }

}
