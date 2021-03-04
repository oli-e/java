package com.example.demo.backend.repository;

import com.example.demo.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p " +
            "where lower(p.productName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.productPrice) like lower(concat('%', :searchTerm, '%'))") //
    List<Product> search(@Param("searchTerm") String searchTerm);

}
