package com.jamilxt.instagram_clone.service;

import com.jamilxt.instagram_clone.dtos.CommentDto;
import com.jamilxt.instagram_clone.dtos.PostDto;
import com.jamilxt.instagram_clone.model.Comment;
import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.model.User;
import com.jamilxt.instagram_clone.repositories.CommentRepository;
import com.jamilxt.instagram_clone.repositories.PostRepository;
import com.jamilxt.instagram_clone.request.CommentRequest;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Page<Post> showAll(
            Optional<Integer> page,
            Optional<String> sortBy) {
//        Pageable pageWithElements;
//        if (sort.equals("NA")) {
//            pageWithElements = PageRequest.of(pageIndex, rows, Sort.by("countryName").ascending());
//        } else {
//            pageWithElements = PageRequest.of(pageIndex, rows, Sort.by("countryName").descending());
//        }
        return postRepository.findAll(
                PageRequest.of(page.orElse(0), 10,
                        Sort.Direction.DESC, sortBy.orElse("postId")));
    }

    public Page<Post> getPostByUser(User user,
                                    Optional<Integer> page,
                                    Optional<String> sortBy) {
        return postRepository.findByUser(user,
                PageRequest.of(page.orElse(0), 10,
                        Sort.Direction.DESC, sortBy.orElse("postId")));
    }


    public void save(PostDto postDto, User user) {

        var postEntity = new Post();
        BeanUtils.copyProperties(postDto, postEntity);
        postEntity.setUser(user);
        postRepository.save(postEntity);

    }

    public void deletePost(Long id) {
        postRepository.deleteById(id); // will be converted to soft delete
    }

    public int totalPostsOfUser(User user) {
        return postRepository.countPostsByUser(user);
    }

    public Optional<Post> singlePost(Long id) {
        return postRepository.findById(id);
    }

    public void addComment(CommentDto commentDto) {
        var commentEntity = new Comment();
        BeanUtils.copyProperties(commentDto, commentEntity);
        commentRepository.save(commentEntity);
    }

    public int totalCommentsOfPost(Post post) {
        return commentRepository.countCommentsByPost(post);
    }

    public List<CommentRequest> getCommentByPost(Post post,
                                                 Optional<Integer> page,
                                                 Optional<String> sortBy) {
        List<CommentRequest> commentRequests = new ArrayList<>();

        for (Comment comment :
                commentRepository.findByPost(post,
                        PageRequest.of(page.orElse(0), 10,
                                Sort.Direction.DESC, sortBy.orElse("commentId")))) {
            CommentRequest commentRequest = new CommentRequest();
            commentRequest.setCommentId(comment.getCommentId());
            commentRequest.setUsername(comment.getUser().getUsername());
            commentRequest.setCommentText(comment.getCommentText());
            commentRequest.setPropic(comment.getUser().getPropic());
            commentRequest.setCreated_at(comment.getPost().getCreated_at().toString());
            commentRequests.add(commentRequest);
        }

        return commentRequests;
    }

    public List<CommentRequest> getCommentByPostFirst(Post post) {
        List<CommentRequest> commentRequests = new ArrayList<>();

        for (Comment comment :
                commentRepository.findByPost(post,
                        PageRequest.of(0, 10,
                                Sort.Direction.ASC, "commentId"))) {
            CommentRequest commentRequest = new CommentRequest();
            commentRequest.setCommentId(comment.getCommentId());
            commentRequest.setUsername(comment.getUser().getUsername());
            commentRequest.setCommentText(comment.getCommentText());
            commentRequest.setPropic(comment.getUser().getPropic());

            PrettyTime p = new PrettyTime();
            String created_at = p.format(new Date(Timestamp.valueOf(comment.getCreated_at()).getTime()));
            commentRequest.setCreated_at(created_at);
            commentRequests.add(commentRequest);
        }

        return commentRequests;
    }
}
