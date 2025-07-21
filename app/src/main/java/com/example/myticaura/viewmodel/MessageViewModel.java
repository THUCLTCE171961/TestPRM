package com.example.myticaura.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myticaura.model.entity.Message;
import com.example.myticaura.repository.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private final MessageRepository messageRepository;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        messageRepository = new MessageRepository(application);
    }

    public LiveData<List<Message>> getChatMessages(int user1Id, int user2Id) {
        return messageRepository.getChatMessages(user1Id, user2Id);
    }

    public void insert(Message message) {
        messageRepository.insert(message);
    }
}


