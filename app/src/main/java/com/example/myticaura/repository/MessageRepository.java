package com.example.myticaura.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.database.AppDatabase;
import com.example.myticaura.model.entity.Message;
import com.example.myticaura.model.dao.MessageDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageRepository {
    private final MessageDao messageDao;
    private final ExecutorService executorService;

    public MessageRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        messageDao = database.messageDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Message>> getChatMessages(int user1Id, int user2Id) {
        return messageDao.getChatMessages(user1Id, user2Id);
    }

    public void insert(Message message) {
        executorService.execute(() -> messageDao.insert(message));
    }
}

