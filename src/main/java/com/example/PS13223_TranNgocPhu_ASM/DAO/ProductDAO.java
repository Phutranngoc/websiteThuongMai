package com.example.PS13223_TranNgocPhu_ASM.DAO;

import java.util.List;

import com.example.PS13223_TranNgocPhu_ASM.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDAO extends JpaRepository<Product, Integer>{

    // @Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    // List<Product> findByPrice(double minPrice, double maxPrice);

    
    // @Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
    // Page<Product> findByKeywords(String keywords, Pageable pageable);

    @Query("SELECT new Report(o.category, sum(o.price), count(o)) " + " FROM Product o " + " GROUP BY o.category"
            + " ORDER BY sum(o.price) DESC")
    List<Report> getInventoryByCategory();

}