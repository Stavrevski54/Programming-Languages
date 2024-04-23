package com.uacs.test2;

import jakarta.persistence.*;

@Entity
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @ManyToOne
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PostComment() {
    }

    public PostComment(String text, Long id) {
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostComment[" + "id=" + id + ", text=" + text + ']';
    }


}