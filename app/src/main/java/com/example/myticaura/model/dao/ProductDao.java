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
    @Query("SELECT * FROM products WHERE " +
            "(:query IS NULL OR name LIKE :query) AND " +
            "(:categoryId IS NULL OR category_id = :categoryId) AND " +
            "(:minPrice IS NULL OR price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR price <= :maxPrice)")
    LiveData<List<Product>> getFilteredProducts(String query, Integer categoryId, Double minPrice, Double maxPrice);
    @Query("SELECT DISTINCT c.name FROM products p JOIN categories c ON p.category_id = c.category_id")
    LiveData<List<String>> getDistinctCategoryNames(); // Giả sử bảng Category có cột 'name'

    /**
     * Phương thức lọc tổng hợp mạnh mẽ
     */
    @Query("SELECT p.* FROM products p " +
            "LEFT JOIN categories c ON p.category_id = c.category_id " +
            "WHERE (:searchQuery = '' OR p.name LIKE '%' || :searchQuery || '%' OR p.description LIKE '%' || :searchQuery || '%') " +
            "AND (:categoryName = 'All Categories' OR c.name = :categoryName) " +
            "AND (:priceFilter = 'All Prices' OR " +
            "    (:priceFilter = 'Dưới 100,000đ' AND p.price < 100000) OR " +
            "    (:priceFilter = '100,000đ - 500,000đ' AND p.price BETWEEN 100000 AND 500000) OR " +
            "    (:priceFilter = 'Trên 500,000đ' AND p.price > 500000))")
    LiveData<List<Product>> filterProducts(String searchQuery, String categoryName, String priceFilter);





}


