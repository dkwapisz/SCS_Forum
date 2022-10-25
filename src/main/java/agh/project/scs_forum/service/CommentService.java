package agh.project.scs_forum.service;

import static java.lang.Math.ceil;

import agh.project.scs_forum.model.Category;
import agh.project.scs_forum.model.Comment;
import agh.project.scs_forum.model.Post;
import agh.project.scs_forum.repository.CommentRepository;
import agh.project.scs_forum.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;
    final int MAX_COMMENTS_IN_PAGE = 20;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    private List<Comment> getAllCommentsFromPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ArrayList<>();
        }

        return commentRepository.findAllByPostId(post.get().getPostId());
    }

    public ResponseEntity<?> getAllCommentsInPost(Long postId) {
        List<Comment> commentList = getAllCommentsFromPost(postId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>("Post does not exist or has no comments", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    public ResponseEntity<?> getCommentsByCreationDateAsc(Long postId, int page) {
        List<Comment> commentList = getAllCommentsFromPost(postId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        commentList = commentList.stream().sorted(Comparator.comparing(Comment::getCreatedAt)).collect(Collectors.toList());

        int allEntities = commentList.size();
        int begin = (page - 1) * MAX_COMMENTS_IN_PAGE;
        int end = page * MAX_COMMENTS_IN_PAGE;

        int numberOfPages = (int) ceil(allEntities / (double) MAX_COMMENTS_IN_PAGE);

        commentList = commentList.stream().skip(begin).limit(end).collect(Collectors.toList());

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    public ResponseEntity<?> getCommentsByCreationDateDesc(Long postId, int page) {
        List<Comment> commentList = getAllCommentsFromPost(postId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        commentList = commentList.stream().sorted(Comparator.comparing(Comment::getCreatedAt).reversed()).collect(Collectors.toList());

        int allEntities = commentList.size();
        int begin = (page - 1) * MAX_COMMENTS_IN_PAGE;
        int end = page * MAX_COMMENTS_IN_PAGE;

        int numberOfPages = (int) ceil(allEntities / (double) MAX_COMMENTS_IN_PAGE);

        commentList = commentList.stream().skip(begin).limit(end).collect(Collectors.toList());

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    public ResponseEntity<?> getCommentsByRatingAsc(Long postId, int page) {
        List<Comment> commentList = getAllCommentsFromPost(postId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>("Cannot found comments", HttpStatus.NOT_FOUND);
        }

        commentList = commentList.stream().sorted(Comparator.comparing(Comment::getRating)).collect(Collectors.toList());

        int allEntities = commentList.size();
        int begin = (page - 1) * MAX_COMMENTS_IN_PAGE;
        int end = page * MAX_COMMENTS_IN_PAGE;

        int numberOfPages = (int) ceil(allEntities / (double) MAX_COMMENTS_IN_PAGE);

        commentList = commentList.stream().skip(begin).limit(end).collect(Collectors.toList());

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    public ResponseEntity<?> getCommentsByRatingDesc(Long postId, int page) {
        List<Comment> commentList = getAllCommentsFromPost(postId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>("Cannot found comments", HttpStatus.NOT_FOUND);
        }

        commentList = commentList.stream().sorted(Comparator.comparing(Comment::getRating).reversed()).collect(Collectors.toList());

        int allEntities = commentList.size();
        int begin = (page - 1) * MAX_COMMENTS_IN_PAGE;
        int end = page * MAX_COMMENTS_IN_PAGE;

        int numberOfPages = (int) ceil(allEntities / (double) MAX_COMMENTS_IN_PAGE);

        commentList = commentList.stream().skip(begin).limit(end).collect(Collectors.toList());

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    public ResponseEntity<?> getCommentByCommentId(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            return new ResponseEntity<>("Cannot found comments", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    public ResponseEntity<?> createComment(Comment newComment, Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ResponseEntity<>("Post with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        Comment comment = new Comment();
        comment.setCreatedBy("x"); // TODO Session
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setRating(0);
        comment.setPostId(postId);
        comment.setText(newComment.getText());
        commentRepository.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editComment(Comment editComment, Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            return new ResponseEntity<>("Comment with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        comment.get().setUpdatedAt(LocalDateTime.now());
        comment.get().setText(editComment.getText());

        commentRepository.save(comment.get());

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    public ResponseEntity<?> increaseCommentRanking(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            return new ResponseEntity<>("Comment with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        comment.get().setRating(comment.get().getRating() + 1);

        commentRepository.save(comment.get());

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    public ResponseEntity<?> decreaseCommentRanking(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            return new ResponseEntity<>("Comment with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        comment.get().setRating(comment.get().getRating() - 1);

        commentRepository.save(comment.get());

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            return new ResponseEntity<>("Comment with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        commentRepository.delete(comment.get());

        return new ResponseEntity<>("Comment has been deleted.", HttpStatus.OK);
    }
}
