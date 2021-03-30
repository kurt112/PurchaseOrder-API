package com.API.PurchaseOrder.controller.RestController;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
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

    @PostMapping("/re-login")
    public ResponseEntity<HashMap<?,?>> ReLogin(@RequestParam("token") String token) {

        HashMap<String, Object> hashMap = new HashMap<>();

        String email = jwt.getUsername(token);

        User user = userService.findByEmail(email);

        hashMap.put("token", token);
        hashMap.put("message", "Login Successful");
        hashMap.put("user",user);
        return ResponseEntity.ok(hashMap);
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

        return userService.data("",0,15);
    }

    @PostMapping("/register")
    public ResponseEntity<?> user(@RequestBody User user){

        User user1 = new User(0,0,"kurtoriouqe112@gmail.com","asdf","123","kurt","Oriouqe","Model",1,new Date(), new Date(), new Date());

        HashMap<String, Object> hashMap = new HashMap<>();

        if(userService.save(user) == null){
            hashMap.put("message", "Can't Add User");
            return ResponseEntity.badRequest().body(hashMap);
        }

        hashMap.put("data",user);
        hashMap.put("message", "User Register Succesful");
        return ResponseEntity.ok(hashMap);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") int id){
        User user = userService.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        if(user == null){
            hashMap.put("message", "Can't Delete User With the id of " + id);

            return ResponseEntity.badRequest().body(hashMap);
        }

        userService.deleteById(id);

        hashMap.put("message", "Delete User Successful");
        hashMap.put("data", user);
          return ResponseEntity.ok(hashMap);
    }

}
