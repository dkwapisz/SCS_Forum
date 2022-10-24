package agh.project.scs_forum.service;

import agh.project.scs_forum.model.Category;
import agh.project.scs_forum.model.Post;
import agh.project.scs_forum.repository.CategoryRepository;
import agh.project.scs_forum.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    PostRepository postRepository;
    CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    private List<Post> getAllPostsFromCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);

        if (category == null) {
            return new ArrayList<>();
        }

        return postRepository.findAllByCategoryId(category.getCategoryId());
    }

    public ResponseEntity<?> getPostsByCreationDateAsc(String categoryName) {
        List<Post> postList = getAllPostsFromCategory(categoryName);

        if (postList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        postList = postList.stream().sorted(Comparator.comparing(Post::getCreatedAt)).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    public ResponseEntity<?> getPostsByCreationDateDesc(String categoryName) {
        List<Post> postList = getAllPostsFromCategory(categoryName);

        if (postList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        postList = postList.stream().sorted(Comparator.comparing(Post::getCreatedAt).reversed()).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    public ResponseEntity<?> getPostsByModificationDateAsc(String categoryName) {
        List<Post> postList = getAllPostsFromCategory(categoryName);

        if (postList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        postList = postList.stream().sorted(Comparator.comparing(Post::getUpdatedAt)).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    public ResponseEntity<?> getPostsByModificationDateDesc(String categoryName) {
        List<Post> postList = getAllPostsFromCategory(categoryName);

        if (postList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        postList = postList.stream().sorted(Comparator.comparing(Post::getUpdatedAt).reversed()).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    public ResponseEntity<?> getPostsByAlphabeticAsc(String categoryName) {
        List<Post> postList = getAllPostsFromCategory(categoryName);

        if (postList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        postList = postList.stream().sorted(Comparator.comparing(Post::getTitle)).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    public ResponseEntity<?> getPostsByAlphabeticDesc(String categoryName) {
        List<Post> postList = getAllPostsFromCategory(categoryName);

        if (postList.isEmpty()) {
            return new ResponseEntity<>("Cannot found posts", HttpStatus.NOT_FOUND);
        }

        postList = postList.stream().sorted(Comparator.comparing(Post::getTitle).reversed()).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    public ResponseEntity<?> getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ResponseEntity<>("Post with given ID does not exist.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> createPost(Post newPost, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isEmpty()) {
            return new ResponseEntity<>("Category with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        Post post = new Post();
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
        post.setRating(0);
        post.setCategoryId(categoryId);
        post.setCreatedBy("x"); // TODO Session

        postRepository.save(post);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editPost(Post editedPost, Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ResponseEntity<>("Post with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        post.get().setTitle(editedPost.getTitle());
        post.get().setDescription(editedPost.getDescription());
        post.get().setUpdatedAt(LocalDateTime.now());

        postRepository.save(post.get());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    public ResponseEntity<?> increasePostRanking(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ResponseEntity<>("Post with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        post.get().setRating(post.get().getRating() + 1);

        postRepository.save(post.get());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    public ResponseEntity<?> decreasePostRanking(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ResponseEntity<>("Post with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        post.get().setRating(post.get().getRating() - 1);

        postRepository.save(post.get());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return new ResponseEntity<>("Post with given ID does not exist.", HttpStatus.NOT_FOUND);
        }

        postRepository.delete(post.get());

        return new ResponseEntity<>("Post has been deleted.", HttpStatus.OK);
    }

//    private Long postId;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private Long categoryId;
//    private String createdBy;
//    private String title;
//    private String description;
//    private Integer rating;
}
