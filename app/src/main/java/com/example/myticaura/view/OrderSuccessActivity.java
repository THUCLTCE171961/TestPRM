package com.example.myticaura.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myticaura.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderSuccessActivity extends AppCompatActivity {

    private TextView textOrderId, textOrderDate, textTotalAmount, textShippingAddress;
    private Button buttonBackHome, buttonViewOrders;

    public static final String EXTRA_ORDER_ID = "order_id";
    public static final String EXTRA_TOTAL_AMOUNT = "total_amount";
    public static final String EXTRA_SHIPPING_ADDRESS = "shipping_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        initViews();
        loadOrderData();
        setupClickListeners();
    }

    private void initViews() {
        textOrderId = findViewById(R.id.text_order_id);
        textOrderDate = findViewById(R.id.text_order_date);
        textTotalAmount = findViewById(R.id.text_total_amount);
        textShippingAddress = findViewById(R.id.text_shipping_address);
        buttonBackHome = findViewById(R.id.button_back_home);
        buttonViewOrders = findViewById(R.id.button_view_orders);
    }

    private void loadOrderData() {
        Intent intent = getIntent();
        
        // Get order data from intent
        int orderId = intent.getIntExtra(EXTRA_ORDER_ID, 0);
        double totalAmount = intent.getDoubleExtra(EXTRA_TOTAL_AMOUNT, 0.0);
        String shippingAddress = intent.getStringExtra(EXTRA_SHIPPING_ADDRESS);

        // Display order ID
        textOrderId.setText("#" + orderId);

        // Display current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
        textOrderDate.setText(dateFormat.format(new Date()));

        // Display total amount
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        textTotalAmount.setText(currencyFormat.format(totalAmount));

        // Display shipping address
        if (shippingAddress != null && !shippingAddress.isEmpty()) {
            textShippingAddress.setText(shippingAddress);
        } else {
            textShippingAddress.setText("Chưa cập nhật địa chỉ");
        }
    }

    private void setupClickListeners() {
        buttonBackHome.setOnClickListener(v -> {
            // Navigate back to MainActivity with Home fragment
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("FRAGMENT", "home");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        buttonViewOrders.setOnClickListener(v -> {
            // Navigate to MainActivity with Orders fragment
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("FRAGMENT", "orders");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Navigate to home when back is pressed
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FRAGMENT", "home");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}