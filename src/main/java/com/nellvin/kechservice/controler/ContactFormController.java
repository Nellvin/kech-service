package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Event;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ContactFormController {

    @PostMapping("/api/contact-form")
    public void sendEmail(@RequestBody Event event) {
        System.out.println("Email sent!");
    }
}
