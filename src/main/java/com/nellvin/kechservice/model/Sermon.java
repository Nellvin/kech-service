package com.nellvin.kechservice.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "SERMON")
public class Sermon {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long  id;

    @Column(name = "SERMON_NAME", length = 64)
    private String name;

    @Column(name = "SERMON_DESC", length = 64)
    private String desc;

    @CreatedDate
    @Column(name = "SERMON_DATE")
    private Date date;

    @Column(name = "SERMON_URL", length = 64)
    private String url;

    @Column(length = 64)
    private String photo;

    @Column(length = 64)
    private String audio;

    public Long  getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photos) {
        this.photo = photos;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}

