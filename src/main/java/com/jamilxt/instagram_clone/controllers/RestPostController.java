package com.jamilxt.instagram_clone.controllers;

import com.jamilxt.instagram_clone.dtos.CommentDto;
import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.model.User;
import com.jamilxt.instagram_clone.request.CommentRequest;
import com.jamilxt.instagram_clone.request.PostRequest;
import com.jamilxt.instagram_clone.service.BaseService;
import com.jamilxt.instagram_clone.service.PostService;
import com.jamilxt.instagram_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RestPostController extends BaseService {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @PostMapping("/post/comments/add")
    public ResponseEntity<?> addNewComment(@RequestParam(name = "postId") long postId, @RequestParam(name = "commentText") String commentText) {

        Post post = postService.singlePost(postId).get();
//        User user = (User) userService.loadUserByUsername("jamilxt");
        CommentRequest commentRequest = postService.addComment(new CommentDto(commentText, post, getLoggedInUser()));
        commentRequest.setTotalComments(postService.totalCommentsOfPost(post));
//        CommentRequest commentRequest = postService.addComment(new CommentDto(commentText, post, user));

        return new ResponseEntity<>(commentRequest, HttpStatus.OK);

    }

    @GetMapping("/post/comments")
    public List<CommentRequest> getCommentsByPost(@RequestParam(name = "postId") long postId, @RequestParam(value = "page") Optional<Integer> page,
                                                  @RequestParam(value = "sortBy") Optional<String> sortBy) {

        Post post = postService.singlePost(postId).get();
        return postService.getCommentByPost(post, page, sortBy);

    }

    @GetMapping("/posts")
    public List<PostRequest> getPostsByUser(@RequestParam(name = "username") String username, @RequestParam(value = "page") Optional<Integer> page,
                                            @RequestParam(value = "sortBy") Optional<String> sortBy) {
        User user = (User) userService.loadUserByUsername(username);
        return postService.getPostByUserRest(user, page, sortBy);
    }
}
