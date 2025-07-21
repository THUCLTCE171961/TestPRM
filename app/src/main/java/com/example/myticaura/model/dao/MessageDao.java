package com.example.myticaura.model.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.myticaura.model.entity.Message;
import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Query("SELECT * FROM messages WHERE (sender_id = :user1Id AND receiver_id = :user2Id) OR (sender_id = :user2Id AND receiver_id = :user1Id) ORDER BY timestamp ASC")
    LiveData<List<Message>> getChatMessages(int user1Id, int user2Id);
}


