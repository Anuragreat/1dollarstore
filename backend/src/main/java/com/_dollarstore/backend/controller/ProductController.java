package com._dollarstore.backend.controller;
import com._dollarstore.backend.model.Product;
import com._dollarstore.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("amount") double amount,
                              @RequestParam("image") MultipartFile file) {
        // You can save image to local folder or cloud, then save path in DB
        String imageUrl = "/images/" + file.getOriginalFilename(); // You can improve this logic
        Product product = new Product(null, name, description, amount, imageUrl);
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
