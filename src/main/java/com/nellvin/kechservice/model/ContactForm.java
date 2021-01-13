package com.nellvin.kechservice.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACTFORM")
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CONTACTFORM_NAME", length = 64)
    private String name;

    @Column(name = "CONTACTFORM_EMAIL")
    private String email;

    @Column(name = "CONTACTFORM_PHONE")
    private String phoneNumber;

    @Column(name = "CONTACTFORM_MESSAGE", columnDefinition="CLOB NOT NULL")
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
