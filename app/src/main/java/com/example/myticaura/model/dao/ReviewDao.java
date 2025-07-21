package com.example.myticaura.model.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myticaura.model.entity.Review;
import java.util.List;

@Dao
public interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Review review);

    @Query("SELECT * FROM reviews WHERE product_id = :productId ORDER BY created_at DESC")
    LiveData<List<Review>> getReviewsForProduct(int productId);
}



