package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.itschool.entity.Product;
import ro.itschool.entity.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByBrandIgnoreCase(String name);

    List<Product> findByDeletedIsFalse();

    @Query(
            value = "SELECT * FROM product p WHERE p.brand LIKE %:keyword% OR p.model LIKE %:keyword% OR p.price LIKE %:keyword% " +
                    "OR p.deleted LIKE %:keyword%",
            nativeQuery = true)
    List<Product> searchProduct(@Param("keyword") String keyword);





}
