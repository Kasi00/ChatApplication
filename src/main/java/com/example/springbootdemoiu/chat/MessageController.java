package com.example.springbootdemoiu.chat;

import com.example.springbootdemoiu.chat.persistance.Message;
import com.example.springbootdemoiu.chat.user.persistance.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {

        String receiver = message.getReceiver();
        if (receiver == null || receiver.isEmpty()) {
            throw new IllegalArgumentException("Receiver cannot be null or empty");
        }

        // Save the message
        return messageService.saveMessage(message);
    }

    @GetMapping("/chatroom/{chatroomId}")
    public List<Message> findByChatroomId(@PathVariable Long chatroomId){
        return messageService.findByChatroomId(chatroomId);
    }

    @GetMapping("/chat/{sender}/{receiver}")
    public List<Message> getMessagesBetweenUsers(@PathVariable User sender, @PathVariable String receiver) {
        return messageService.findMessagesBetweenUsers(sender, receiver);
    }



}
