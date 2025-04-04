package com.example.springbootdemoiu.chat.user;

import com.example.springbootdemoiu.chat.error.InvalidLoginException;
import com.example.springbootdemoiu.chat.user.auth.LoginRequest;
import com.example.springbootdemoiu.chat.user.auth.LoginResponse;
import com.example.springbootdemoiu.chat.user.persistance.Role;
import com.example.springbootdemoiu.chat.user.persistance.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Value("${jwt.secret-key}")
    private String secretkey;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest request) {
        User user=userService.getUser(request.getUsername());
        System.out.println("Entered Password: " + request.getPassword());
        System.out.println("Stored Hashed Password: " + user.getPassword());
        if(passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            return generateLoginResponse(user);


        }
        throw new InvalidLoginException("Invalid login");
    }


    public LoginResponse generateLoginResponse(User user){

        try{
            JWTClaimsSet claims= new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .claim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                    .issuer("chatapp.ge")
                    .expirationTime(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)).build();

            JWSHeader header= new JWSHeader(JWSAlgorithm.HS256);
            SignedJWT signedJWT= new SignedJWT(header, claims);
            signedJWT.sign(new MACSigner(secretkey.getBytes()));


            return new LoginResponse(signedJWT.serialize());

        }catch (Exception e){
            throw new InvalidLoginException("Failed to generate a token");
        }


    }




}
