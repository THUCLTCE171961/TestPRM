package com.example.myticaura.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.myticaura.model.entity.Cart;
import com.example.myticaura.model.entity.CartWithItems;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Cart cart);

    @Transaction
    @Query("SELECT * FROM carts WHERE user_id = :userId LIMIT 1")
    LiveData<CartWithItems> getCartWithItemsByUserId(int userId);

    @Query("SELECT * FROM carts WHERE user_id = :userId LIMIT 1")
    Cart findCartByUserId(int userId);

    @Query("DELETE FROM carts WHERE user_id = :userId")
    void deleteCartByUserId(int userId);
}

