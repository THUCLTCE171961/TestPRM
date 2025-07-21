package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.Cart;
import com.example.myticaura.model.entity.CartItem;
import com.example.myticaura.model.entity.CartWithItems;
import com.example.myticaura.model.dao.CartDao;
import com.example.myticaura.model.dao.CartItemDao;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartRepository {
    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final ExecutorService executorService;

    public CartRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        cartDao = database.cartDao();
        cartItemDao = database.cartItemDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<CartWithItems> getCart(int userId) {
        return cartDao.getCartWithItemsByUserId(userId);
    }

    public void addProductToCart(int userId, int productId, int quantity) {
        executorService.execute(() -> {
            Cart cart = cartDao.findCartByUserId(userId);
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(userId);
                cart.setCreatedAt(new Date());
                long cartId = cartDao.insert(cart);
                cart.setCartId((int) cartId);
            }

            CartItem item = cartItemDao.findItemInCart(cart.getCartId(), productId);
            if (item == null) {
                item = new CartItem();
                item.setCartId(cart.getCartId());
                item.setProductId(productId);
                item.setQuantity(quantity);
                cartItemDao.insert(item);
            } else {
                item.setQuantity(item.getQuantity() + quantity);
                cartItemDao.update(item);
            }
        });
    }

    public void updateCartItem(CartItem cartItem) {
        executorService.execute(() -> cartItemDao.update(cartItem));
    }

    public void removeCartItem(CartItem cartItem) {
        executorService.execute(() -> cartItemDao.delete(cartItem));
    }

    public void clearCart(int userId) {
        executorService.execute(() -> cartDao.deleteCartByUserId(userId));
    }
}

