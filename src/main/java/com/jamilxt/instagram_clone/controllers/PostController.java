package com.jamilxt.instagram_clone.controllers;

import com.jamilxt.instagram_clone.dtos.PostDto;
import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.model.User;
import com.jamilxt.instagram_clone.service.PostService;
import com.jamilxt.instagram_clone.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    ServletContext context;

    @Autowired
    private PostService postService;

    @GetMapping("/post/show-all")
//    @ResponseBody
    public String post_showAll(Model model,
                               @RequestParam(value = "caption") Optional<String> caption,
                               @RequestParam(value = "page") Optional<Integer> page,
                               @RequestParam(value = "sortBy") Optional<String> sortBy) {
        model.addAttribute("message", "Showing all posts");
        model.addAttribute("pageTitle", "Post List");
        model.addAttribute("posts", postService.showAll(caption, page, sortBy));
        return "post/show-all";
//        return photoService.showAll(caption, page, sortBy);
    }

    @GetMapping("/post/add")
    public String post_add(Model model) {
        model.addAttribute("pageTitle", "Add Post");
        model.addAttribute("post", new PostDto());
        return "post/add";
    }

    @PostMapping("/post/add")
    public String post_add_POST(@ModelAttribute("post") Post post, @RequestParam("file") MultipartFile file, HttpSession httpSession) {

        User userEntity = (User) httpSession.getAttribute("authUser");

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
                Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
                Files.write(path, bytes);
                post.setUrl(file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }


        var postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
        postService.save(postDto, userEntity);
        return "redirect:/post/show-all";
    }

    @GetMapping("/post/delete")
    public String deletePost(Model model, @RequestParam("id") long id) {

        postService.deletePost(id);
        model.addAttribute("message", "Post deleted successfully");
        return "redirect:/post/show-all";

    }

}
