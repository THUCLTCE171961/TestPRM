package com.example.myticaura.model.entity;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "carts",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "user_id",
                childColumns = "user_id",
                onDelete = ForeignKey.CASCADE))
public class Cart {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    private int cartId;

    @ColumnInfo(name = "user_id", index = true)
    private int userId;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    // Getters and Setters
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}



