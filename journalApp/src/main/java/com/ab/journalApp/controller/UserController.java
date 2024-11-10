package com.ab.journalApp.controller;

import com.ab.journalApp.entity.JournalEntity;
import com.ab.journalApp.entity.User;
import com.ab.journalApp.service.JournalEntryService;
import com.ab.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User>getAllUsers(){
        return  userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
         userService.saveEntry(user);

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user ){
       User userInDb =  userService.findByUserName(user.getUserName());
       if(userInDb != null){
           userInDb.setUserName(user.getUserName());
           userInDb.setPassword(user.getPassword());
           userService.saveEntry(userInDb);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
