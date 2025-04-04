package com.example.springbootdemoiu.chat.persistance;

import com.example.springbootdemoiu.chat.user.persistance.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="messages",schema = "chat")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column
    private String receiver;

    @Column
    private String content;


}
