package com.example.myticaura.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myticaura.model.entity.Product;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertAll(Product... products);

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM products WHERE product_id = :productId")
    LiveData<Product> getProductById(int productId);

    @Query("SELECT * FROM products WHERE category_id = :categoryId")
    LiveData<List<Product>> getProductsByCategory(int categoryId);

    @Query("SELECT * FROM products WHERE name LIKE '%' || :searchQuery || '%' OR description LIKE '%' || :searchQuery || '%'")
    LiveData<List<Product>> searchProducts(String searchQuery);

}

