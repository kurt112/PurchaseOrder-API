package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.configuration.Login.AuthenticationRequest;
import com.API.PurchaseOrder.entity.API.LogoutPost;
import com.API.PurchaseOrder.entity.API.UserPost;
import com.API.PurchaseOrder.entity.Sector;
import com.API.PurchaseOrder.entity.User;
import com.API.PurchaseOrder.service.MyUserDetailsService;
import com.API.PurchaseOrder.service.serviceImplementation.SectorService;
import com.API.PurchaseOrder.service.serviceImplementation.UserService;
import com.API.PurchaseOrder.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    final private UserService userService;
    final private SectorService sectorService;
    private final Jwt jwt;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService, SectorService sectorService, Jwt jwt, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService) {
        this.userService = userService;
        this.sectorService = sectorService;
        this.jwt = jwt;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;

    }

    @PostMapping("/logout")
    public ResponseEntity<List<HashMap>> LogoutMethod(@RequestParam("token") String token){

        List<HashMap> response = new ArrayList<>();
        jwt.removeToken(token);
        HashMap<String,Object> data = new HashMap<>();

        LogoutPost logoutPost = new LogoutPost("Logout Success");
        data.put("data", logoutPost);
        response.add(data);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/re-login")
    public ResponseEntity<List<HashMap>> ReLogin(@RequestParam("token") String token) {
        System.out.println(token);
        HashMap<String, Object> hashMap = new HashMap<>();

        String email = jwt.getUsername(token);

        User user = userService.findByEmail(email);

        UserPost userPost = new UserPost(user,token);
        List<UserPost> userPosts = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        userPosts.add(userPost);
        hashMap.put("message","hoy kupal nagLogin ka ulit");
        hashMap.put("data",userPost);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<List<HashMap>> Login(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("wew");
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }catch (Exception badCredentialsException){
            System.out.println(badCredentialsException);
            hashMap.put("message", "Account Not Found");
            hashMap.put("error",badCredentialsException);

            return ResponseEntity.badRequest().body(null);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = this.jwt.generateToken(userDetails);

        UserPost userPost = new UserPost(userService.findByEmail(userDetails.getUsername()),jwt);
        List<UserPost> userPosts = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        userPosts.add(userPost);
        hashMap.put("message","hoy kupal nakalogin ka example lang yan haha");
        hashMap.put("data",userPost);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/addUpdate")
    public ResponseEntity<?> user(@RequestBody User user){

        User find = userService.findById(user.getId());

        HashMap<String, Object> hashMap = new HashMap<>();
        if(find == null){
            user.setCreateAt(new Date());
            hashMap.put("message", "User Register Successful");
        }else{
            user.setUpdateAt(new Date());
            hashMap.put("message", "User Update Successful");
        }
        if(userService.save(user) == null){
            hashMap.put("message", "Can't Add User");
            return ResponseEntity.badRequest().body(hashMap);
        }
        hashMap.put("data",user);

        return ResponseEntity.ok(hashMap);
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestParam("id") int id){

        HashMap<String, Object> response = new HashMap<>();
        User user = userService.findById(id);

        if(user == null){
            response.put("message", "Can't find user with the id of " + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.put("data", user);
        response.put("message", "User Find Success");

        return ResponseEntity.ok(response);
    }
    @GetMapping("/list")
    public ResponseEntity<?> getUsers(@RequestParam("search") String search, @RequestParam("page") int page,
                                      @RequestParam("size") int size){
        HashMap<String, Object> response = new HashMap<>();
        Page<User> users = userService.data(search,page-1,size);
        response.put("data", users.getContent());
        response.put("totalElements", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());
        response.put("currentPage", users.getNumber()+1);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") int id){
        User user = userService.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        if(user == null){
            hashMap.put("message", "Can't Delete User With the id of " + id);

            return ResponseEntity.badRequest().body(hashMap);
        }


        user.setDeleteAt(new Date());
        hashMap.put("message", "Delete User Successful");
        hashMap.put("data", user);
          return ResponseEntity.ok(hashMap);
    }

    @GetMapping("/getApprover")
    public ResponseEntity<?> getApprovers(@RequestParam("search") String search, @RequestParam("page") int page,
                                      @RequestParam("size") int size){
        HashMap<String, Object> response = new HashMap<>();
        Page<User> users = userService.approver(search,page-1,size);
        response.put("data", users.getContent());
        response.put("totalElements", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());
        response.put("currentPage", users.getNumber()+1);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(){
        HashMap<String, Object> response = new HashMap<>();
        Sector sector = new Sector(0,"",3,4,0, new Date(),new Date(),new Date());
        User current = userService.findById(1);
        if( current == null){
            current = new User(0,sector.getId(), "JohnDoe@gmail.com","johndoe","1234567","John","Doe",1,1,new Date(),new Date(), new Date());
            userService.save(current);
            sectorService.save(sector);
        }
        response.put("admin", current);
        return ResponseEntity.ok(response);
    }

}
