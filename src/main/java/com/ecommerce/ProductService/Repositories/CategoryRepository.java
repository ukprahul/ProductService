package com.ecommerce.ProductService.Repositories;

import com.ecommerce.ProductService.Modals.Category;
import com.ecommerce.ProductService.Modals.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//first we need to extend to JpaRepository
//Then we have to give the class name and datatype of primary key

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);

    @Query("SELECT c.title FROM Category c")
    List<String> findAllCategoryTitles();


    @Query("SELECT p FROM Product p WHERE p.category.title=:title")
    List<Product> findProductsByCategoryTitle(@Param("title") String title);
}
