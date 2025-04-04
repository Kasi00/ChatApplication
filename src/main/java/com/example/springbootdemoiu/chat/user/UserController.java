package com.example.springbootdemoiu.chat.user;

import com.example.springbootdemoiu.chat.error.NotFoundException;
import com.example.springbootdemoiu.chat.user.model.UserRequest;
import com.example.springbootdemoiu.chat.user.persistance.Role;
import com.example.springbootdemoiu.chat.user.persistance.RoleRepository;
import com.example.springbootdemoiu.chat.user.persistance.User;
import com.example.springbootdemoiu.chat.user.persistance.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.example.springbootdemoiu.chat.security.AuthorizationConstants.ADMIN;
import static com.example.springbootdemoiu.chat.security.AuthorizationConstants.USER_OR_ADMIN;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;

    @GetMapping
    @PreAuthorize(ADMIN)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


   @GetMapping("/{id}")
   @PreAuthorize(USER_OR_ADMIN)
   public ResponseEntity<User> getUser(@PathVariable Long id) {
       User user= userService.findUser(id).orElseThrow(()->new NotFoundException("user not found"));
       return ResponseEntity.ok(user);
   }




   @PostMapping("/create")
   @PreAuthorize(USER_OR_ADMIN)
    public void createUser(@RequestBody @Valid UserRequest userRequest) {
       userService.createUser(userRequest);
   }

   @PutMapping("/{id}")
   @PreAuthorize(USER_OR_ADMIN)
    public ResponseEntity<User> updateUser(@PathVariable Long id , @RequestBody @Valid UserRequest userRequest) {
       User user = userService.updateUser(id,userRequest);
       return ResponseEntity.ok(user);
   }



}
