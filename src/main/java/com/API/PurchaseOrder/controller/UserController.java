package com.API.PurchaseOrder.controller;

import com.API.PurchaseOrder.configuration.Login.AuthenticationRequest;
import com.API.PurchaseOrder.entity.API.LogoutPost;
import com.API.PurchaseOrder.entity.API.UserPost;
import com.API.PurchaseOrder.entity.User;
import com.API.PurchaseOrder.service.MyUserDetailsService;
import com.API.PurchaseOrder.service.serviceImplementation.UserService;
import com.API.PurchaseOrder.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    final private UserService userService;
    private final Jwt jwt;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService, Jwt jwt, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwt = jwt;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/logoutUser")
    public ResponseEntity<List<HashMap>> LogoutMethod(@RequestParam("token") String token){

        List<HashMap> response = new ArrayList<>();
        jwt.removeToken(token);
        HashMap<String,Object> data = new HashMap<>();

        LogoutPost logoutPost = new LogoutPost("Logout Success","");
        data.put("data", logoutPost);
        response.add(data);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<List<HashMap>> Login(@RequestBody AuthenticationRequest authenticationRequest) {

        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }catch (Exception badCredentialsException){
            hashMap.put("message", "Account Not Found");

            return ResponseEntity.badRequest().body(null);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = this.jwt.generateToken(userDetails);

        UserPost userPost = new UserPost(userDetailsService.getUser().getEmail(),userDetailsService.getUser().getPassword(),jwt);
        List<UserPost> userPosts = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        userPosts.add(userPost);
        hashMap.put("message","hoy kupal nakalogin ka example lang yan haha");
        hashMap.put("data",userPost);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public List<User> users(){

        return userService.data("",0);
    }

    @PostMapping("/register")
    public User user(){
        User user = new User("kurtorioque112@gmail.com", "password");
        System.out.println(user.toString());
        userService.save(user);
        return user;
    }

}
