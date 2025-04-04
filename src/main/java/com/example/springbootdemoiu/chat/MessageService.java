package com.example.springbootdemoiu.chat;

import com.example.springbootdemoiu.chat.persistance.Message;
import com.example.springbootdemoiu.chat.persistance.MessageRepository;
import com.example.springbootdemoiu.chat.user.persistance.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> findByChatroomId(Long chatroomId){
        return messageRepository.findByChatroomId(chatroomId);
    }

    public List<Message> findMessagesBetweenUsers(User sender, String receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }

}
