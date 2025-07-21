package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.User;
import com.example.myticaura.model.dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private final UserDao userDao;
    private final ExecutorService executorService;

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        userDao = database.userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void update(User user) {
        executorService.execute(() -> userDao.update(user));
    }

    // This method needs to be called from a background thread or using a callback/future
    public User findByEmail(String email) {
        // For simplicity, running this synchronously. In a real app, use a callback or async task.
        return userDao.findByEmail(email);
    }

    public LiveData<User> getUserById(int userId) {
        return userDao.getUserById(userId);
    }
}

