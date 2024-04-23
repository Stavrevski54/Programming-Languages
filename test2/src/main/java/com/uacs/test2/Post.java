package com.uacs.test2;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @OneToMany(orphanRemoval = true)
    private Set<Post> comments = new HashSet<>();

    public Post(String text, Long id, Set<Post> comments) {
        this.text = text;
        this.id = id;
        this.comments = comments;
    }

    public Post() {}

    public Set<Post> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Post[" + "id=" + id + ", text=" + text + "]";
    }

}
