package org.softoHiest.eCommerce.repository;

import org.softoHiest.eCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByActiveTrue();

    @Query(value = "SELECT * FROM products p " +
            "WHERE p.active = true " +
            "AND p.stock_quantity > 0 " +
            "AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')) )",
            nativeQuery = true)
    List<Product> searchProduct(@Param("keyword") String keyword);
}
