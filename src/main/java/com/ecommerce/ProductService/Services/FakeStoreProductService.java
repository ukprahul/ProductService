package com.ecommerce.ProductService.Services;

import com.ecommerce.ProductService.Modals.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//The code in this fakestoreProductService
//Call the 3rd party api
//We used to send request and get response


// RestTemplate
// - allows to send HTTP requests to external URLs
//    and work with responses


@Service
public class FakeStoreProductService implements productservice {
    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id){
        return null;
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
        return null;
    }

}
