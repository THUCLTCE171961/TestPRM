package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.User;
import com.example.myticaura.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void insert(User user) {
        userRepository.insert(user);
    }


    public void update(User user) {
        userRepository.update(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public LiveData<User> getUserById(int userId) {
        return userRepository.getUserById(userId);
    }
}


