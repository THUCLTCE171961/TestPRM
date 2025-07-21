package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.CartItem;
import com.example.myticaura.model.entity.CartWithItems;
import com.example.myticaura.repository.CartRepository;

public class CartViewModel extends AndroidViewModel {
    private final CartRepository cartRepository;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository(application);
    }

    public LiveData<CartWithItems> getCart(int userId) {
        return cartRepository.getCart(userId);
    }

    public void addProductToCart(int userId, int productId, int quantity) {
        cartRepository.addProductToCart(userId, productId, quantity);
    }

    public void updateCartItem(CartItem cartItem) {
        cartRepository.updateCartItem(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        cartRepository.removeCartItem(cartItem);
    }

    public void clearCart(int userId) {
        cartRepository.clearCart(userId);
    }
}


