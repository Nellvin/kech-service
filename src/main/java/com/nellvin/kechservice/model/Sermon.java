package com.nellvin.kechservice.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SERMON")
public class Sermon {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    private String photoUrl;

    @Column(length = 64)
    private String audioUrl;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photos) {
        this.photoUrl = photos;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audio) {
        this.audioUrl = audio;
    }
}

