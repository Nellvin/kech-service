package com.nellvin.kechservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(
            mappedBy = "gallery",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public boolean addPhoto(Photo photo){
        return photos.add(photo);
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", photos=" + photos +
                '}';
    }
}
