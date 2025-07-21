package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.Order;
import com.example.myticaura.model.entity.OrderItem;
import com.example.myticaura.model.entity.OrderWithItems;
import com.example.myticaura.model.dao.OrderDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderRepository {
    private final OrderDao orderDao;
    private final ExecutorService executorService;

    public OrderRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        orderDao = database.orderDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<OrderWithItems>> getOrdersForUser(int userId) {
        return orderDao.getOrdersForUser(userId);
    }

    public void createOrder(Order order, List<OrderItem> items) {
        executorService.execute(() -> {
            long orderId = orderDao.insertOrder(order);
            for (OrderItem item : items) {
                item.setOrderId((int) orderId);
            }
            orderDao.insertOrderItems(items);
        });
    }
}

