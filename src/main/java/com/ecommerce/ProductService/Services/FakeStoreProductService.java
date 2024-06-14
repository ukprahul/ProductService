package com.ecommerce.ProductService.Services;

import com.ecommerce.ProductService.Exceptions.ProductNotFoundException;
import com.ecommerce.ProductService.Modals.Product;
import com.ecommerce.ProductService.dtos.CreateProductRequestDto;
import com.ecommerce.ProductService.dtos.FakeStoreProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//The code in this fakestoreProductService
//Call the 3rd party api
//We used to send request and get response


// RestTemplate (like a utility)
// - allows to send HTTP requests to external URLs/apis
//    and work with responses


@Service("fakestore")
public class FakeStoreProductService implements productservice {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        //Here I am going to call the external api
        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProduct = fakeStoreProductResponse.getBody();
        if(fakeStoreProduct==null){
            throw new ProductNotFoundException("Product with id: "+ productId+"doesn't exist. Retry some other products");
        }

        return fakeStoreProduct.toProduct();


    }

    @Override
    public List<Product> getAllProducts() {
       FakeStoreProductDto[] fakeStoreProducts =  restTemplate.getForObject(
               "https://fakestoreapi.com/products",
               FakeStoreProductDto[].class);

       List<Product> products = new ArrayList<>();
       for(FakeStoreProductDto fakeStoreproduct:fakeStoreProducts){
           products.add(fakeStoreproduct.toProduct());
       }
       return products;
    }


    //create product
    @Override
    public Product createProduct(String title, double price, String description, String image,
                                 String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto, //requestBody
                FakeStoreProductDto.class); // datatype of response
        return response.toProduct();

    }

    //delete product

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + productId;
        try{
            restTemplate.delete(url);
        }
        catch (HttpClientErrorException e){
            if(e.getStatusCode()== HttpStatus.NOT_FOUND){
                throw new ProductNotFoundException("Product with id: "+ productId+"doesn't exist. So Product can't be deleted");
            }
        }
    }

    @Override
    public List<Product> getSpecificCategory() {
        return null;
    }

    //    Update Product
    @Override
    public Product updateProduct(Long productId,String title,double price, String description, String image,
                                 String category) throws ProductNotFoundException
    {
        FakeStoreProductDto responseUpdate = new FakeStoreProductDto();
        responseUpdate.setTitle(title);
        responseUpdate.setPrice(price);
        responseUpdate.setDescription(description);
        responseUpdate.setImage(image);
        responseUpdate.setCategory(category);
        String url = "https://fakestoreapi.com/products/" + productId;
        try {
            restTemplate.put(url, responseUpdate);
        } catch (HttpClientErrorException e)
        {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException("Product with id: " + productId + "doesn't exist. So Product can't be updated");
            } else {
                throw e;
            }
        }
        return responseUpdate.toProduct();

    }


}





