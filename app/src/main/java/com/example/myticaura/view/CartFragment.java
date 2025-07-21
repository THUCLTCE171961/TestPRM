package com.example.myticaura.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myticaura.R;
import com.example.myticaura.adapter.CartItemAdapter;
import com.example.myticaura.model.entity.CartItem;
import com.example.myticaura.model.entity.Order;
import com.example.myticaura.model.entity.OrderItem;
import com.example.myticaura.utils.SessionManager;
import com.example.myticaura.viewmodel.CartViewModel;
import com.example.myticaura.viewmodel.OrderViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private OrderViewModel orderViewModel;
    private CartItemAdapter adapter;
    private TextView emptyCartText, totalPriceText;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(requireContext());
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        emptyCartText = view.findViewById(R.id.empty_cart_text);
        totalPriceText = view.findViewById(R.id.total_price_text);
        Button checkoutButton = view.findViewById(R.id.checkout_button);

        setupRecyclerView(view);

        cartViewModel.getCart(sessionManager.getUserId()).observe(getViewLifecycleOwner(), cartWithItems -> {
            if (cartWithItems == null || cartWithItems.items.isEmpty()) {
                emptyCartText.setVisibility(View.VISIBLE);
                adapter.submitList(new ArrayList<>());
                totalPriceText.setText("Tổng cộng: 0đ");
            } else {
                emptyCartText.setVisibility(View.GONE);
                adapter.submitList(cartWithItems.items);
                double total = 0;
                for (var item : cartWithItems.items) {
                    total += item.product.getPrice() * item.cartItem.getQuantity();
                }
                NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                totalPriceText.setText(String.format("Tổng cộng: %s", format.format(total)));
            }
        });

        checkoutButton.setOnClickListener(v -> checkout());
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartItemAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setListener(new CartItemAdapter.CartItemListener() {
            @Override
            public void onIncreaseQuantity(CartItem cartItem) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartViewModel.updateCartItem(cartItem);
            }

            @Override
            public void onDecreaseQuantity(CartItem cartItem) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    cartViewModel.updateCartItem(cartItem);
                } else {
                    cartViewModel.removeCartItem(cartItem);
                }
            }

            @Override
            public void onRemoveItem(CartItem cartItem) {
                cartViewModel.removeCartItem(cartItem);
            }
        });
    }

    private void checkout() {
        cartViewModel.getCart(sessionManager.getUserId()).observe(getViewLifecycleOwner(), cartWithItems -> {
            if (cartWithItems != null && !cartWithItems.items.isEmpty()) {
                // Tạo Order
                Order order = new Order();
                order.setUserId(sessionManager.getUserId());
                order.setOrderDate(new Date());
                order.setStatus("Đang xử lý");
                double total = 0;
                List<OrderItem> orderItems = new ArrayList<>();

                for(var item : cartWithItems.items) {
                    total += item.product.getPrice() * item.cartItem.getQuantity();
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(item.product.getProductId());
                    orderItem.setQuantity(item.cartItem.getQuantity());
                    orderItem.setPriceAtPurchase(item.product.getPrice());
                    orderItems.add(orderItem);
                }
                order.setTotalAmount(total);

                // Get user's address for shipping
                String shippingAddress = ""; // Will be loaded from user profile
                
                // Thêm order vào DB
                orderViewModel.createOrder(order, orderItems);

                // Xoá giỏ hàng
                cartViewModel.clearCart(sessionManager.getUserId());

                // Navigate to Order Success Activity
                android.content.Intent intent = new android.content.Intent(getActivity(), OrderSuccessActivity.class);
                intent.putExtra(OrderSuccessActivity.EXTRA_ORDER_ID, System.currentTimeMillis() % 100000); // Generate temporary ID
                intent.putExtra(OrderSuccessActivity.EXTRA_TOTAL_AMOUNT, total);
                intent.putExtra(OrderSuccessActivity.EXTRA_SHIPPING_ADDRESS, shippingAddress);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Giỏ hàng trống!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


