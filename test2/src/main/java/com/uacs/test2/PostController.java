package com.uacs.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @PutMapping("/[id]")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @RequestMapping
    public ResponseEntity<Post> getPostById(@RequestParam Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/[id]")
    public ResponseEntity<Post> getPostByPostId(@RequestParam Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/[id]")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}