package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Event;
import com.nellvin.kechservice.model.User;
import com.nellvin.kechservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    public List<User> retrieveUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long eventId) {
        return userRepository.findById(eventId).get();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
