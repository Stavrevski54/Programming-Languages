package com.uacs.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;

    @PostMapping
    public ResponseEntity<PostComment> createPostComment(@RequestBody PostComment postComment) {
        return ResponseEntity.ok(postCommentService.createPostComment(postComment));
    }

    @GetMapping
    public ResponseEntity<List<PostComment>> getPostComments() {
        return ResponseEntity.ok(postCommentService.getAllPostComments());
    }

    @GetMapping("[id]")
    public ResponseEntity<PostComment> getPostCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(postCommentService.getPostCommentById(id));
    }

    @PutMapping("[id]")
    public ResponseEntity<PostComment> updatePostComment(@PathVariable Long id, @RequestBody PostComment postComment) {
        postComment.setId(id);
        return ResponseEntity.ok(postCommentService.updatePostComment(postComment));
    }

    @DeleteMapping("[id]")
    public ResponseEntity<PostComment> deletePostComment(@PathVariable Long id) {
        postCommentService.deletePostComment(id);
        return ResponseEntity.ok().build();
    }
}
