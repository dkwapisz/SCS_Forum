package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.Post;
import agh.project.scs_forum.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/category/{categoryName}/post/creation/asc")
    public ResponseEntity<?> getPostsByCreationDateAsc(@PathVariable String categoryName) {
        return postService.getPostsByCreationDateAsc(categoryName);
    }

    @GetMapping("/category/{categoryName}/post/creation/desc")
    public ResponseEntity<?> getPostsByCreationDateDesc(@PathVariable String categoryName) {
        return postService.getPostsByCreationDateDesc(categoryName);
    }

    @GetMapping("/category/{categoryName}/post/modified/asc")
    public ResponseEntity<?> getPostsByModificationDateAsc(@PathVariable String categoryName) {
        return postService.getPostsByModificationDateAsc(categoryName);
    }

    @GetMapping("/category/{categoryName}/post/modified/desc")
    public ResponseEntity<?> getPostsByModificationDateDesc(@PathVariable String categoryName) {
        return postService.getPostsByModificationDateDesc(categoryName);
    }

    @GetMapping("/category/{categoryName}/post/alphabetic/asc")
    public ResponseEntity<?> getPostsByAlphabeticAsc(@PathVariable String categoryName) {
        return postService.getPostsByAlphabeticAsc(categoryName);
    }

    @GetMapping("/category/{categoryName}/post/alphabetic/desc")
    public ResponseEntity<?> getPostsByAlphabeticDesc(@PathVariable String categoryName) {
        return postService.getPostsByAlphabeticDesc(categoryName);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/category/{categoryId}/post")
    public ResponseEntity<?> createPost(@RequestBody Post post, @PathVariable Long categoryId) {
        return postService.createPost(post, categoryId);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<?> editPost(@RequestBody Post editedPost, @PathVariable Long postId) {
        return postService.editPost(editedPost, postId);
    }

    @PutMapping("/post/{postId}/ranking/increase")
    public ResponseEntity<?> increasePostRanking(@PathVariable Long postId) {
        return postService.increasePostRanking(postId);
    }

    @PutMapping("/post/{postId}/ranking/decrease")
    public ResponseEntity<?> decreasePostRanking(@PathVariable Long postId) {
        return postService.decreasePostRanking(postId);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }
}
