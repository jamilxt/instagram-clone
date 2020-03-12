package com.jamilxt.instagram_clone.controllers;

import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

@Controller
public class PhotoControllerRest {

    @Autowired
    ServletContext context;

    @Autowired
    private PostService postService;


//    @GetMapping("/photo/show-all-rest")
//    @ResponseBody
//    public Page<Post> photo_showAll_rest(
//            @RequestParam(value = "username") String username) {
//        return postService.getPostByUser(username);
//    }

}
