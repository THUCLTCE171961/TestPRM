package com.example.myticaura.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myticaura.R;
import com.example.myticaura.adapter.ReviewAdapter;
import com.example.myticaura.model.entity.Product;
import com.example.myticaura.viewmodel.CartViewModel;
import com.example.myticaura.viewmodel.ProductViewModel;
import com.example.myticaura.viewmodel.ReviewViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private ReviewViewModel reviewViewModel;
    private CartViewModel cartViewModel;

    private ImageView productImageView;
    private TextView productNameView, productPriceView, productDescriptionView;
    private Button addToCartButton;
    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;

    private int currentProductId;
    private int currentUserId = 1; // Giả sử user ID là 1 để test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        currentProductId = getIntent().getIntExtra("PRODUCT_ID", -1);
        if (currentProductId == -1) {
            finish(); // Không có product ID thì đóng activity
            return;
        }

        // Khởi tạo Views
        productImageView = findViewById(R.id.detail_product_image);
        productNameView = findViewById(R.id.detail_product_name);
        productPriceView = findViewById(R.id.detail_product_price);
        productDescriptionView = findViewById(R.id.detail_product_description);
        addToCartButton = findViewById(R.id.button_add_to_cart);
        reviewsRecyclerView = findViewById(R.id.recycler_view_reviews);

        // Khởi tạo ViewModels
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        reviewViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Setup RecyclerView cho reviews
        setupReviewRecyclerView();

        // Lấy và hiển thị dữ liệu
        loadProductDetails();
        loadReviews();

        // Xử lý sự kiện
        addToCartButton.setOnClickListener(v -> {
            cartViewModel.addProductToCart(currentUserId, currentProductId, 1);
            Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupReviewRecyclerView() {
        reviewAdapter = new ReviewAdapter();
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewAdapter);
    }

    private void loadProductDetails() {
        productViewModel.getProductById(currentProductId).observe(this, product -> {
            if (product != null) {
                updateProductUI(product);
            }
        });
    }

    private void loadReviews() {
        reviewViewModel.getReviewsForProduct(currentProductId).observe(this, reviews -> {
            reviewAdapter.submitList(reviews);
        });
    }

    private void updateProductUI(Product product) {
        productNameView.setText(product.getName());
        productDescriptionView.setText(product.getDescription());
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        productPriceView.setText(format.format(product.getPrice()));

        Glide.with(this)
                .load(product.getImageUrl())
                .into(productImageView);
    }
}


