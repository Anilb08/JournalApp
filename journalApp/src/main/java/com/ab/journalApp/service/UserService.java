package com.ab.journalApp.service;

import com.ab.journalApp.entity.JournalEntity;
import com.ab.journalApp.entity.User;
import com.ab.journalApp.repo.JournalEntryRepository;
import com.ab.journalApp.repo.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return  userRepository.findAll();
    }

   public Optional<User> findById(ObjectId id){
        return  userRepository.findById(id);
   }

   // deleting service
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
       return userRepository.findByUserName(userName);
    }
}
