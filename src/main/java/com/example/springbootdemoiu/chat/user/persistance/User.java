package com.example.springbootdemoiu.chat.user.persistance;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "chat")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name= "user_roles",
            schema = "chat",
            joinColumns = @JoinColumn(name= "user_id"),
            inverseJoinColumns = @JoinColumn (name="role_id")

    )
    private Set<Role> roles= new HashSet<>();


    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
