package com.nellvin.kechservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GALLERY_NAME", length = 64)
    private String name;

    @Column(name = "GALLERY_CREATEDATE")
    private Date createDate;

    @Column(name = "GALLERY_AUTHOR", length = 64)
    private String author;

    @OneToMany(
            mappedBy = "gallery",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Photo> photos;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
