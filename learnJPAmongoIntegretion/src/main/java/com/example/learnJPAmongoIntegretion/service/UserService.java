package com.example.learnJPAmongoIntegretion.service;


import com.example.learnJPAmongoIntegretion.entity.JournalEntry;
import com.example.learnJPAmongoIntegretion.entity.User;
import com.example.learnJPAmongoIntegretion.repository.UserRepository;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username){
         return userRepository.findByUsername(username);
    }

}
