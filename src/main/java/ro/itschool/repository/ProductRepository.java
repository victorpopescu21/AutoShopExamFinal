package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByBrandIgnoreCase(String name);

//    List<Product> findByDeletedIsFalse();



}
