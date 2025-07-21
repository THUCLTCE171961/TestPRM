package com.example.myticaura.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "products",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "category_id",
                childColumns = "category_id",
                onDelete = ForeignKey.SET_NULL))
public class Product {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private int productId;

    private String name;
    private String description;
    private double price;

    @ColumnInfo(name = "stock_quantity")
    private int stockQuantity;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "category_id", index = true)
    private Integer categoryId; // Use Integer to allow NULL on delete

    public Product(String name, String description, double price, int stockQuantity, String imageUrl, Integer categoryId, String material) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.material = material;
    }

    private String material;

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}



