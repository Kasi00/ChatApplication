package com.example.springbootdemoiu.chat.user.model;

import com.example.springbootdemoiu.chat.user.persistance.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private final String username;
    @NotBlank
    @Size(min = 2)
    private final String password;
    @NotEmpty
    private Set<Long> roleIds;


}
