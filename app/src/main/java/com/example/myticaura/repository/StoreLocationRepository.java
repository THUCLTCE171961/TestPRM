package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.StoreLocation;
import com.example.myticaura.model.dao.StoreLocationDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StoreLocationRepository {
    private final StoreLocationDao storeLocationDao;
    private final ExecutorService executorService;

    public StoreLocationRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        storeLocationDao = database.storeLocationDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<StoreLocation>> getAllLocations() {
        return storeLocationDao.getAllLocations();
    }

    public void insert(StoreLocation... locations) {
        executorService.execute(() -> storeLocationDao.insertAll(locations));
    }
}

