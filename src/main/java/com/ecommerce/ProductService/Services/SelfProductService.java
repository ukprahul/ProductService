package com.ecommerce.ProductService.Services;

import com.ecommerce.ProductService.Exceptions.ProductNotFoundException;
import com.ecommerce.ProductService.Modals.Category;
import com.ecommerce.ProductService.Modals.Product;
import com.ecommerce.ProductService.Repositories.CategoryRepository;
import com.ecommerce.ProductService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("selfService")
public class SelfProductService implements productservice {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        return productRepository.findByIdIs(productId);
        //for exception you can also do like Product product = ....
//        if (product == null) {
//            throw new ProductNotFoundException("Product not found with id: " + productId);
//        }
//        return product;
    }


    @Override
    public List<Product> getAllProducts() {
//        List<products>
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(category);
        if(categoryFromDatabase==null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDatabase = newCategory;
        }
        product.setCategory(categoryFromDatabase);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        productRepository.deleteById(productId);
    }

    @Override
    public List<String> getAllCategories()
    {
        return categoryRepository.findAllCategoryTitles();
    }

    @Override
    public List<Product> getSpecificCategoryProducts(String item) {
        return categoryRepository.findProductsByCategoryTitle(item);
    }

    @Override
    public Product updateProduct(Long productId, String title, double price, String description, String image, String category) {
        return null;
    }
}
