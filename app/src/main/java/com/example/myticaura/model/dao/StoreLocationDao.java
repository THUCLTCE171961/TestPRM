package com.example.myticaura.model.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.myticaura.model.entity.StoreLocation;
import java.util.List;

@Dao
public interface StoreLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(StoreLocation... locations);

    @Query("SELECT * FROM store_locations")
    LiveData<List<StoreLocation>> getAllLocations();
}



