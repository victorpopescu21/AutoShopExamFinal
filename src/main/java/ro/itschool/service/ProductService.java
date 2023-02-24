package ro.itschool.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getProductsByBrand(String name);

    List<Product> searchForProduct(String keyword);
}
