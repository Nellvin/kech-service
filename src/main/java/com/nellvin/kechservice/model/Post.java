package com.nellvin.kechservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;

    @Column(name = "POST_NAME", length = 64)
    private String title;

    @Column(name = "POST_CONTENT",columnDefinition="CLOB NOT NULL")
    private String content;

    @Column(name = "POST_CREATEDATE")
    private Date createDate;

    @Column(name = "POST_FILE", length = 64)
    private String filePath;

    @Column(name = "POST_AUTHOR", length = 64)
    private String author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", filePath='" + filePath + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
