package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.Order;
import com.example.myticaura.model.entity.OrderItem;
import com.example.myticaura.model.entity.OrderWithItems;
import com.example.myticaura.repository.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private final OrderRepository orderRepository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
    }

    public LiveData<List<OrderWithItems>> getOrdersForUser(int userId) {
        return orderRepository.getOrdersForUser(userId);
    }

    public void createOrder(Order order, List<OrderItem> items) {
        orderRepository.createOrder(order, items);
    }
}


