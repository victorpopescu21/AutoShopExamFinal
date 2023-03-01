package ro.itschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Product;
import ro.itschool.repository.CarRepo;
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.ProductService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getProductsByBrand(String name) {
        return productRepository.findByBrandIgnoreCase(name);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(Objects.requireNonNullElse(keyword,""));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


}
