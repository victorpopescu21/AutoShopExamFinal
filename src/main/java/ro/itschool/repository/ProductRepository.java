package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.itschool.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByBrandIgnoreCase(String name);


    @Query(value ="SELECT * FROM product p WHERE p.id LIKE %:keyword% OR p.brand LIKE %:keyword% OR p.model LIKE %:keyword% OR p.vehicle_type LIKE %:keyword%",
            nativeQuery = true)
    List<Product> searchProduct(@Param("keyword") String keyword);





}
