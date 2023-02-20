package ro.itschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getProductsByBrand(String name) {
        return productRepository.findByBrandIgnoreCase(name);
    }
}
