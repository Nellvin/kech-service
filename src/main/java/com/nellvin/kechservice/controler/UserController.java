package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Role;
import com.nellvin.kechservice.model.User;
import com.nellvin.kechservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.retrieveUsers();
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/api/users/login")
    public User loginUser(@RequestBody User user) {
        if(user.getUserName().equals("user") && user.getPassword().equals("user"))
            return new User("user", "user", Role.user);
        if(user.getUserName().equals("admin") && user.getPassword().equals("admin") )
            return new User("admin", "admin", Role.administrator);
        if(userService.retrieveUsers().stream().map(user1 -> user1.getUserName()+" "+ user1.getPassword()).anyMatch(namePassword -> namePassword.equals(user.getUserName()+" "+user.getPassword()))){
            return new User(user.getUserName(), user.getPassword(), Role.user);
        }
        return null;
    }

    @PostMapping("/api/users")
    public User saveUser(@RequestBody User user) {
       return userService.saveUser(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable(name = "id") Long userId) {
        userService.deleteUser(userId);
        System.out.println("User " + userId + " has been deleted");
    }

    @PutMapping("/api/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable(name = "id") Long userId) {
        User eve = userService.getUser(userId);
        if (eve != null)
            userService.updateUser(user);

    }
}
