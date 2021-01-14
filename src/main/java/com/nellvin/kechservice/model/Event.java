package com.nellvin.kechservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENT")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  id;

    @Column(name = "EVENT_NAME", length = 64)
    private String name;

    @Column(name = "EVENT_ADDRESS")
    private String address;

    @Column(name = "EVENT_TIME")
    private Date datetime;

    public Event(){
    }

    public Event(String name){
        this.name = name;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
