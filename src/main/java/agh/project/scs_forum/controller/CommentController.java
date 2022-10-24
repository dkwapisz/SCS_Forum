package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.Comment;
import agh.project.scs_forum.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}/comment/creation/asc")
    public ResponseEntity<?> getCommentsByCreationDateAsc(@PathVariable Long postId) {
        return commentService.getCommentsByCreationDateAsc(postId);
    }

    @GetMapping("/post/{postId}/comment/creation/desc")
    public ResponseEntity<?> getCommentsByCreationDateDesc(@PathVariable Long postId) {
        return commentService.getCommentsByCreationDateDesc(postId);
    }

    @GetMapping("/post/{postId}/comment/rating/asc")
    public ResponseEntity<?> getCommentsByRatingAsc(@PathVariable Long postId) {
        return commentService.getCommentsByRatingAsc(postId);
    }

    @GetMapping("/post/{postId}/comment/rating/desc")
    public ResponseEntity<?> getCommentsByRatingDesc(@PathVariable Long postId) {
        return commentService.getCommentsByRatingDesc(postId);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<?> getCommentByCommentId(@PathVariable Long commentId) {
        return commentService.getCommentByCommentId(commentId);
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<?> createComment(@RequestBody Comment newComment, @PathVariable Long postId) {
        return commentService.createComment(newComment, postId);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> editComment(@RequestBody Comment editComment, @PathVariable Long commentId) {
        return commentService.editComment(editComment, commentId);
    }

    @PutMapping("/comment/{commentId}/ranking/increase")
    public ResponseEntity<?> increaseCommentRanking(@PathVariable Long commentId) {
        return commentService.increaseCommentRanking(commentId);
    }

    @PutMapping("/comment/{commentId}/ranking/decrease")
    public ResponseEntity<?> decreaseCommentRanking(@PathVariable Long commentId) {
        return commentService.decreaseCommentRanking(commentId);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

}
