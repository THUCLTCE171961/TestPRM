package com.example.myticaura.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.myticaura.model.entity.Product;
import com.example.myticaura.repository.ProductRepository;

import java.util.List;
import java.util.Objects;

public class ProductViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;

    // --- SỬA 1: Nâng cấp FilterParams để chứa cả giá ---
    private static class FilterParams {
        final String query;
        final Integer categoryId;
        final Double minPrice; // Dùng Double để có thể là null
        final Double maxPrice; // Dùng Double để có thể là null

        FilterParams(String query, Integer categoryId, Double minPrice, Double maxPrice) {
            this.query = query;
            this.categoryId = categoryId;
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
        }
    }

    private final MutableLiveData<FilterParams> filters = new MutableLiveData<>();
    private final LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);

        // --- SỬA 2: Nâng cấp switchMap để gọi một phương thức lọc tổng hợp ---
        products = Transformations.switchMap(filters, params -> {
            // Để code gọn gàng, chúng ta nên có một phương thức trong Repository
            // có khả năng nhận tất cả các tham số này và xây dựng câu truy vấn SQL tương ứng.
            // Giả sử phương thức đó là getFilteredProducts.
            return productRepository.getFilteredProducts(params.query, params.categoryId, params.minPrice, params.maxPrice);
        });

        // Thiết lập giá trị mặc định để tải tất cả sản phẩm khi bắt đầu
        clearFilters();
    }

    // --- SỬA 3: Thay thế các hàm set riêng lẻ bằng một hàm applyFilters duy nhất ---

    /**
     * Phương thức công khai duy nhất mà Fragment cần quan sát.
     */
    public LiveData<List<Product>> getProducts() {
        return products;
    }

    /**
     * Cập nhật TẤT CẢ các bộ lọc cùng một lúc. Fragment sẽ gọi hàm này.
     */
    public void applyFilters(String query, Integer categoryId, Double minPrice, Double maxPrice) {
        filters.setValue(new FilterParams(query, categoryId, minPrice, maxPrice));
    }

    /**
     * Quay lại trạng thái ban đầu (hiển thị tất cả sản phẩm).
     */
    public void clearFilters() {
        filters.setValue(new FilterParams("", null, null, null));
    }

    // --- Các phương thức khác không đổi ---
    public LiveData<Product> getProductById(int productId) {
        return productRepository.getProductById(productId);
    }
}



