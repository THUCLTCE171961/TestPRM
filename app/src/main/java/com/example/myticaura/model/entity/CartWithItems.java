package com.example.myticaura.model.entity;



import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class CartWithItems {
    @Embedded
    public Cart cart;

    @Relation(
            parentColumn = "cart_id",
            entityColumn = "cart_id",
            entity = CartItem.class
    )
    public List<CartItemWithProduct> items; // Lồng thêm thông tin sản phẩm
}


