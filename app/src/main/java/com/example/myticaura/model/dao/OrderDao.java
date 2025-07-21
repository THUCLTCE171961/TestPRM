package com.example.myticaura.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.myticaura.model.entity.Order;
import com.example.myticaura.model.entity.OrderItem;
import com.example.myticaura.model.entity.OrderWithItems;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    long insertOrder(Order order); // Trả về order_id

    @Insert
    void insertOrderItems(List<OrderItem> items);

    @Transaction
    @Query("SELECT * FROM orders WHERE user_id = :userId ORDER BY order_date DESC")
    LiveData<List<OrderWithItems>> getOrdersForUser(int userId);
}

