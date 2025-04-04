package com.example.springbootdemoiu.chat.user;

import com.example.springbootdemoiu.chat.error.NotFoundException;
import com.example.springbootdemoiu.chat.user.persistance.Role;
import com.example.springbootdemoiu.chat.user.persistance.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new NotFoundException("role not found"));
    }



}
