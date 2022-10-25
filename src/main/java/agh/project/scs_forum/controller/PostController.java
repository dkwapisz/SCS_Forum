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

    @GetMapping("/category/{categoryName}/post/creation/asc/page/{page}")
    public ResponseEntity<?> getPostsByCreationDateAsc(@PathVariable String categoryName, @PathVariable int page) {
        return postService.getPostsByCreationDateAsc(categoryName, page);
    }

    @GetMapping("/category/{categoryName}/post/creation/desc/page/{page}")
    public ResponseEntity<?> getPostsByCreationDateDesc(@PathVariable String categoryName, @PathVariable int page) {
        return postService.getPostsByCreationDateDesc(categoryName, page);
    }

    @GetMapping("/category/{categoryName}/post/modified/asc/page/{page}")
    public ResponseEntity<?> getPostsByModificationDateAsc(@PathVariable String categoryName, @PathVariable int page) {
        return postService.getPostsByModificationDateAsc(categoryName, page);
    }

    @GetMapping("/category/{categoryName}/post/modified/desc/page/{page}")
    public ResponseEntity<?> getPostsByModificationDateDesc(@PathVariable String categoryName, @PathVariable int page) {
        return postService.getPostsByModificationDateDesc(categoryName, page);
    }

    @GetMapping("/category/{categoryName}/post/alphabetic/asc/page/{page}")
    public ResponseEntity<?> getPostsByAlphabeticAsc(@PathVariable String categoryName, @PathVariable int page) {
        return postService.getPostsByAlphabeticAsc(categoryName, page);
    }

    @GetMapping("/category/{categoryName}/post/alphabetic/desc/page/{page}")
    public ResponseEntity<?> getPostsByAlphabeticDesc(@PathVariable String categoryName, @PathVariable int page) {
        return postService.getPostsByAlphabeticDesc(categoryName, page);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/category/{categoryName}/post")
    public ResponseEntity<?> createPost(@RequestBody Post post, @PathVariable String categoryName) {
        return postService.createPost(post, categoryName);
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
