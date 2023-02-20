package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getProductsByBrand(String name);
}
