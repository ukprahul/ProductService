package com.ecommerce.ProductService.Repositories;

import com.ecommerce.ProductService.Modals.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product p);
    Product findByIdIs(long id);
    List<Product> findAll();

    void deleteById(long id);

}
