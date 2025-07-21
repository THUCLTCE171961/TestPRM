package com.example.myticaura.model.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myticaura.model.entity.Category;
import java.util.List;

@Dao
public interface CategoryDao {

    // ✅ Insert 1 đối tượng Category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    // ✅ Insert nhiều đối tượng Category (array)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Category... categories);

    @Query("SELECT * FROM categories ORDER BY name ASC")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM categories WHERE category_id = :categoryId")
    LiveData<Category> getCategoryById(int categoryId);
}
