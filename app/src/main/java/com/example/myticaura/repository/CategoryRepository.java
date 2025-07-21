package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.Category;
import com.example.myticaura.model.dao.CategoryDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryRepository {
    private final CategoryDao categoryDao;
    private final ExecutorService executorService;

    public CategoryRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        categoryDao = database.categoryDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public void insert(Category category) {
        executorService.execute(() -> categoryDao.insertAll(category));
    }
}

