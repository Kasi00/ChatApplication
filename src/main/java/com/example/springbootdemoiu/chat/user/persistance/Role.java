package com.example.springbootdemoiu.chat.user.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="role",schema="chat")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



}
