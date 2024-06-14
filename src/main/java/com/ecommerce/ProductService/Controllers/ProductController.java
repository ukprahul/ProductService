package com.ecommerce.ProductService.Controllers;

import com.ecommerce.ProductService.Exceptions.ProductNotFoundException;
import com.ecommerce.ProductService.Modals.Product;
import com.ecommerce.ProductService.Services.FakeStoreProductService;
import com.ecommerce.ProductService.Services.productservice;
import com.ecommerce.ProductService.dtos.CreateProductRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    //productservice productService = new FakeStoreProductService();
    productservice productService;
    RestTemplate restTemplate;

    public ProductController(@Qualifier("fakestore") productservice productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    //@qualifer is used for to find the dependency to the injected above.
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto
                                         createProductRequestDto
    ) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory());
    }
    //Service method should be generic
    //Should not tightly coupled with controller

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.noContent().build(); // Return 204 No Content if successful
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if product does not exist
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 Internal Server Error for other exceptions
        }
    }
}
    //getProducts from a category first



//    @PutMapping("/products/{id}")
//    public Product updateProduct(@PathVariable("id") Long productId,@RequestBody CreateProductRequestDto createProductRequestDto){
//        return productService.updateProduct(createProductRequestDto.getTitle(),
//                ,
//                createProductRequestDto.getPrice(),
//                createProductRequestDto.getDescription(),
//                createProductRequestDto.getDescription(),
//                createProductRequestDto.getImage(),
//                createProductRequestDto.getCategory());
//    }


