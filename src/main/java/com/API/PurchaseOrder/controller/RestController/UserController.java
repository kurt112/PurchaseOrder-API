package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.configuration.Login.AuthenticationRequest;
import com.API.PurchaseOrder.entity.API.LogoutPost;
import com.API.PurchaseOrder.entity.API.Settings;
import com.API.PurchaseOrder.entity.API.UserGet;
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
        hashMap.put("message","Re Login Success");
        hashMap.put("data",userPost);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("wew");
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            System.out.println(authenticationRequest.getUsername());
            System.out.println(authenticationRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }catch (Exception badCredentialsException){
            System.out.println(badCredentialsException);
            hashMap.put("title", "Account Not Found");
            hashMap.put("message","Please Input A Correct Email and Password");

            return ResponseEntity.badRequest().body(hashMap);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = this.jwt.generateToken(userDetails);

        User user = userService.findByEmail(userDetails.getUsername());
        UserPost userPost = new UserPost(user,jwt);
        List<UserPost> userPosts = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        userPosts.add(userPost);
        hashMap.put("message","Login Success");
        hashMap.put("data",userPost);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/addUpdate")
    public ResponseEntity<?> user(@RequestBody User user){

        User find = userService.findById(user.getId());
        List<User> list = new ArrayList<>();
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
        list.add(user);
        hashMap.put("data",list);
        hashMap.put("success",true);

        return ResponseEntity.ok(hashMap);
    }


    @PostMapping("/list")
    public ResponseEntity<?> getUsers(@RequestBody Settings settings){
        HashMap<String, Object> response = new HashMap<>();
        Page<User> users = userService.data(settings.getSearch(), settings.getCurrentPage()-1,settings.getPageSize(),settings.getOrderBy(),false);
        List<UserGet> userGets = new ArrayList<>();
        users.getContent().forEach(e->{
            userGets.add(new UserGet(e));
        });
        response.put("status", true);
        response.put("totalItem", users.getTotalElements());
        response.put("totalPage", users.getTotalPages());
        response.put("pageSize", settings.getPageSize());
        response.put("currentPage", users.getNumber()+1);
        response.put("data", userGets);


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

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestParam("id") int id){

        HashMap<String, Object> response = new HashMap<>();
        User user = userService.findById(id);
        List<User> userList = new ArrayList<>();

        if(user == null){
            response.put("message", "Can't find user with the id of " + id);
            return ResponseEntity.badRequest().body(response);
        }
        userList.add(user);
        response.put("data", userList);
        response.put("message", "User Find Success");
        response.put("success",true);
        return ResponseEntity.ok(response);
    }

}
