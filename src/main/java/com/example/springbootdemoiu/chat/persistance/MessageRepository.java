package com.example.springbootdemoiu.chat.persistance;

import com.example.springbootdemoiu.chat.user.persistance.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatroomId(Long chatroomId);


    List<Message> findBySenderAndReceiver(User sender, String receiver);
}

