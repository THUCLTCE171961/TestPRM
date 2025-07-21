package com.example.myticaura.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "messages")
public class Message {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id")
    private int messageId;

    @ColumnInfo(name = "sender_id")
    private int senderId;

    @ColumnInfo(name = "receiver_id")
    private int receiverId;

    @ColumnInfo(name = "message_text")
    private String messageText;

    private Date timestamp;

    // Getters and Setters
    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}


