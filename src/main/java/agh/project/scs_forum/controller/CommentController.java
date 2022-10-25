package agh.project.scs_forum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import agh.project.scs_forum.model.Comment;
import agh.project.scs_forum.service.CommentService;

@RestController
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<?> getAllCommentsInPost(@PathVariable Long postId) {
        return commentService.getAllCommentsInPost(postId);
    }

    @GetMapping("/post/{postId}/comment/creation/asc/page/{page}")
    public ResponseEntity<?> getCommentsByCreationDateAsc(@PathVariable Long postId, @PathVariable int page) {
        return commentService.getCommentsByCreationDateAsc(postId, page);
    }

    @GetMapping("/post/{postId}/comment/creation/desc/page/{page}")
    public ResponseEntity<?> getCommentsByCreationDateDesc(@PathVariable Long postId, @PathVariable int page) {
        return commentService.getCommentsByCreationDateDesc(postId, page);
    }

    @GetMapping("/post/{postId}/comment/rating/asc/page/{page}")
    public ResponseEntity<?> getCommentsByRatingAsc(@PathVariable Long postId, @PathVariable int page) {
        return commentService.getCommentsByRatingAsc(postId, page);
    }

    @GetMapping("/post/{postId}/comment/rating/desc/page/{page}")
    public ResponseEntity<?> getCommentsByRatingDesc(@PathVariable Long postId, @PathVariable int page) {
        return commentService.getCommentsByRatingDesc(postId, page);
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
