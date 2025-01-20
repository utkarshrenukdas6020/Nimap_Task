package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductWithCategory(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            Category category = product.getCategory();
            if (category != null) {
                product.setCategory(category);  // Ensures category data is populated
            }
        }
        return product;
    }
}