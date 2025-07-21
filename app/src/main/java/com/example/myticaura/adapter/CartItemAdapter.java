package com.example.myticaura.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myticaura.R;
import com.example.myticaura.model.entity.CartItem;
import com.example.myticaura.model.entity.CartItemWithProduct; // Sử dụng POJO này
import com.example.myticaura.model.entity.Product;

import java.text.NumberFormat;
import java.util.Locale;

public class CartItemAdapter extends ListAdapter<CartItemWithProduct, CartItemAdapter.CartItemViewHolder> {

    private CartItemListener listener;

    public CartItemAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CartItemWithProduct> DIFF_CALLBACK = new DiffUtil.ItemCallback<CartItemWithProduct>() {
        @Override
        public boolean areItemsTheSame(@NonNull CartItemWithProduct oldItem, @NonNull CartItemWithProduct newItem) {
            return oldItem.cartItem.getCartItemId() == newItem.cartItem.getCartItemId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItemWithProduct oldItem, @NonNull CartItemWithProduct newItem) {
            return oldItem.cartItem.getQuantity() == newItem.cartItem.getQuantity() &&
                    oldItem.product.getProductId() == newItem.product.getProductId();
        }
    };

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, quantity;
        ImageButton increaseBtn, decreaseBtn, removeBtn;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cart_product_image);
            productName = itemView.findViewById(R.id.cart_product_name);
            productPrice = itemView.findViewById(R.id.cart_product_price);
            quantity = itemView.findViewById(R.id.text_view_quantity);
            increaseBtn = itemView.findViewById(R.id.button_increase_quantity);
            decreaseBtn = itemView.findViewById(R.id.button_decrease_quantity);
            removeBtn = itemView.findViewById(R.id.button_remove_item);

            increaseBtn.setOnClickListener(v -> handleAction(listener::onIncreaseQuantity));
            decreaseBtn.setOnClickListener(v -> handleAction(listener::onDecreaseQuantity));
            removeBtn.setOnClickListener(v -> handleAction(listener::onRemoveItem));
        }

        private void handleAction(Action action) {
            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                action.execute(getItem(position).cartItem);
            }
        }

        public void bind(CartItemWithProduct cartItemWithProduct) {
            CartItem cartItem = cartItemWithProduct.cartItem;
            Product product = cartItemWithProduct.product;

            productName.setText(product.getName());
            quantity.setText(String.valueOf(cartItem.getQuantity()));
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            productPrice.setText(format.format(product.getPrice()));

            Glide.with(itemView.getContext()).load(product.getImageUrl()).into(productImage);
        }
    }

    @FunctionalInterface
    interface Action {
        void execute(CartItem cartItem);
    }

    public interface CartItemListener {
        void onIncreaseQuantity(CartItem cartItem);
        void onDecreaseQuantity(CartItem cartItem);
        void onRemoveItem(CartItem cartItem);
    }

    public void setListener(CartItemListener listener) {
        this.listener = listener;
    }
}


