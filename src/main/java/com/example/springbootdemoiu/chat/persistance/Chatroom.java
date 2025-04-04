package com.example.springbootdemoiu.chat.persistance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="chatrooms",schema ="chat")
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
