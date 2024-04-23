package com.uacs.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public PostComment createPostComment(PostComment postComment) {
        return postCommentRepository.save(postComment);
    }

    public List<PostComment> getAllPostComments() {
        return postCommentRepository.findAll();
    }

    public PostComment getPostCommentById(Long id) {
        return postCommentRepository.findById(id).get();
    }

    public PostComment updatePostComment(PostComment postComment) {
        return postCommentRepository.save(postComment);
    }

    public void deletePostComment(Long id) {
        postCommentRepository.deleteById(id);
    }
}
