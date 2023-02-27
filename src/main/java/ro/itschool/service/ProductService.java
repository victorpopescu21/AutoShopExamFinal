package ro.itschool.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;

import java.util.List;

@Service
public interface ProductService {

   public List<Product> getProductsByBrand(String name);

    public List<Product> searchForProduct(String keyword);

    public List<Product> getAllProducts();

    public Product saveProduct(Product product);

}
