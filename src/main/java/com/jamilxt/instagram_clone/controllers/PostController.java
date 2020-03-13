package com.jamilxt.instagram_clone.controllers;

import com.jamilxt.instagram_clone.dtos.PostDto;
import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.model.User;
import com.jamilxt.instagram_clone.request.CommentRequest;
import com.jamilxt.instagram_clone.service.PostService;
import com.jamilxt.instagram_clone.service.UserService;
import com.jamilxt.instagram_clone.util.Constants;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    ServletContext context;

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/post/show-all")
//    @ResponseBody
    public String post_showAll(Model model,
                               @RequestParam(value = "username") Optional<String> username,
                               @RequestParam(value = "page") Optional<Integer> page,
                               @RequestParam(value = "sortBy") Optional<String> sortBy) {
        model.addAttribute("message", "Showing all posts");
        model.addAttribute("pageTitle", "Post List");
        if (username.isPresent()) {
            User userEntity = (User) userService.loadUserByUsername(username.get());
            model.addAttribute("posts", postService.getPostByUser(userEntity, page, sortBy));
        } else {
            model.addAttribute("posts", postService.showAll(page, sortBy));
        }
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
    public String post_add_POST(@ModelAttribute("post") Post post, @RequestParam("files") List<MultipartFile> multipartFiles, HttpSession httpSession) {

        User userEntity = (User) httpSession.getAttribute("authUser");

        List<String> postImages = new ArrayList<>();

        for (MultipartFile file :
                multipartFiles) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
                    Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
                    Files.write(path, bytes);
                    postImages.add(file.getOriginalFilename());
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }

        post.setImages(postImages);

        var postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
//        postDto.setImages(postImages);
        postService.save(postDto, userEntity);
        return "redirect:/post/show-all";
    }

    @GetMapping("/post/delete")
    public String deletePost(Model model, @RequestParam("id") long id) {

        postService.deletePost(id);
        model.addAttribute("message", "Post deleted successfully");
        return "redirect:/post/show-all";

    }

    @GetMapping("/p/{postId}")
    public String singlePost(Model model, @PathVariable(value = "postId") String postId) {
        model.addAttribute("postId", postId);
        Post post = postService.singlePost(Long.parseLong(postId)).get();
//        List<CommentRequest> comments = postService.getCommentByPostFirst(post);
        model.addAttribute("singlePost", post);
        PrettyTime p = new PrettyTime();
        String postedAt = p.format(new Date(Timestamp.valueOf(post.getCreated_at()).getTime()));
        model.addAttribute("postedAt", postedAt);
//        model.addAttribute("comments", comments);
        model.addAttribute("totalComments", postService.totalCommentsOfPost(post));
        return "post/view";
    }


}
