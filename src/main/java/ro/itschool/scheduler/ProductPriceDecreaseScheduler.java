package ro.itschool.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.itschool.service.ProductService;
@Component
public class ProductPriceDecreaseScheduler {

    @Autowired
    private ProductService productService;

    @Scheduled(cron = "*/30 * * * * *")
    public void decreaseProductPrice(){
        productService.getAllProducts()
                .stream()
                .filter(product -> product.getPrice() >= 50000)
                .forEach(product -> {
                    product.setPrice(product.getPrice() - product.getPrice()/5);
                    productService.saveProduct(product);
                });
    }
}
