package com.nellvin.kechservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PHOTO")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PHOTO_NAME", length = 64)
    private String name;

    @Column(name = "PHOTO_alt")
    private String alt;

    @Column(name = "PHOTO_CREATEDATE")
    private Date createDate;

    @Column(name = "PHOTO_FILE", length = 64)
    private String filePath;

    @Column(name = "PHOTO_AUTHOR", length = 64)
    private String author;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
//    @Column(name = "PHOTO_GALLERY")
    private Gallery gallery;

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

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}