package ro.itschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;
import ro.itschool.repository.CarRepo;
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.ProductService;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CarRepo carRepo;
    @Override
    public List<Product> getProductsByBrand(String name) {
        return productRepository.findByBrandIgnoreCase(name);
    }

    @Override
    public List<Product> searchForProduct(String keyword) {
        return productRepository.searchProduct(Objects.requireNonNullElse(keyword,""));
    }

}
