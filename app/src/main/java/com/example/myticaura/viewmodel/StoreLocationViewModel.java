package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.StoreLocation;
import com.example.myticaura.repository.StoreLocationRepository;

import java.util.List;

public class StoreLocationViewModel extends AndroidViewModel {
    private final StoreLocationRepository storeLocationRepository;

    public StoreLocationViewModel(@NonNull Application application) {
        super(application);
        storeLocationRepository = new StoreLocationRepository(application);
    }

    public LiveData<List<StoreLocation>> getAllLocations() {
        return storeLocationRepository.getAllLocations();
    }

    public void insert(StoreLocation... locations) {
        storeLocationRepository.insert(locations);
    }
}


