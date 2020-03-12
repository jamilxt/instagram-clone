package com.jamilxt.instagram_clone.service;

import com.jamilxt.instagram_clone.dtos.PostDto;
import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.model.User;
import com.jamilxt.instagram_clone.repositories.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

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
}
