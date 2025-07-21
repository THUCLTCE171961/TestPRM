package com.example.myticaura.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items",
        foreignKeys = {
                @ForeignKey(entity = Cart.class,
                        parentColumns = "cart_id",
                        childColumns = "cart_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Product.class,
                        parentColumns = "product_id",
                        childColumns = "product_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_item_id")
    private int cartItemId;

    @ColumnInfo(name = "cart_id", index = true)
    private int cartId;

    @ColumnInfo(name = "product_id", index = true)
    private int productId;

    private int quantity;

    // Getters and Setters
    public int getCartItemId() { return cartItemId; }
    public void setCartItemId(int cartItemId) { this.cartItemId = cartItemId; }
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}



