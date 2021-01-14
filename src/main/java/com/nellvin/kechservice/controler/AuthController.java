package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Post;
import com.nellvin.kechservice.model.User;
import com.nellvin.kechservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthController {
//
//        @CrossOrigin
//        @GetMapping("/api/auth")
//        public boolean getAllPosts(@RequestParam String userName, String password) {
//            if(userName.equals("admin") && password.equals("admin")){
//                System.out.println("Logged in");
//                return true;
//            }
//            else{
//                System.out.println("Login refused");
//                return false;
//            }
//        }
//
//    @RequestMapping("/api/login")
//    public boolean login(@RequestBody User user) {
//        return user.getUserName().equals("admin") && user.getPassword().equals("admin");
//    }
//
//    @RequestMapping("/api/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//                .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//                .decode(authToken)).split(":")[0];
//    }
}
