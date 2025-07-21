package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.Product;
import com.example.myticaura.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private final ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public LiveData<List<Product>> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public LiveData<Product> getProductById(int productId) {
        return productRepository.getProductById(productId);
    }

    public LiveData<List<Product>> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    public LiveData<List<Product>> searchProducts(String searchQuery) {
        return productRepository.searchProducts(searchQuery);
    }

    public void insert(Product... products) {
        productRepository.insert(products);
    }

}


