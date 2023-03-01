package ro.itschool.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

   List<Product> getProductsByBrand(String name);

    List<Product> searchProduct(String keyword);

    List<Product> getAllProducts();

    Product saveProduct(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);
}
