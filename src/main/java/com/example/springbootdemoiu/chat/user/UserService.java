package com.example.springbootdemoiu.chat.user;

import com.example.springbootdemoiu.chat.error.NotFoundException;
import com.example.springbootdemoiu.chat.user.model.UserRequest;
import com.example.springbootdemoiu.chat.user.persistance.Role;
import com.example.springbootdemoiu.chat.user.persistance.RoleRepository;
import com.example.springbootdemoiu.chat.user.persistance.User;
import com.example.springbootdemoiu.chat.user.persistance.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;



    public void createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoleIds()
                .stream()
                .map(roleService::getRole)
                .collect(Collectors.toSet()));
        userRepository.save(user);

    }


    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("user not found"));
    }




    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserRequest request) {
        // Fetch user from repository
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        // Update user details
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Fetch and set roles
        Set<Role> roles = request.getRoleIds().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new NotFoundException("Role not found with id: " + roleId)))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        // Save and return updated user
        return userRepository.save(user);
    }
















}
