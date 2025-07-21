package com.example.myticaura.model.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class CartItemWithProduct {
    @Embedded
    public CartItem cartItem;

    @Relation(
            parentColumn = "product_id",
            entityColumn = "product_id"
    )
    public Product product;
}

