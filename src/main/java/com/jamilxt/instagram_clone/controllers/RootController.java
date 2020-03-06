package com.jamilxt.instagram_clone.controllers;

import com.jamilxt.instagram_clone.model.Authority;
import com.jamilxt.instagram_clone.model.User;
import com.jamilxt.instagram_clone.repositories.UserRepository;
import com.jamilxt.instagram_clone.service.AuthorityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

// For showing index.jsp instead of showing the "not found page" error
@Controller
public class RootController {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;

    public RootController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/accounts/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        generateRoles();
        generateUsers();
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("error", error);
        return "/accounts/login";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }

    private void generateRoles() {
        authorityService.create(new Authority(System.nanoTime(), "ROLE_ADMIN"));
        authorityService.create(new Authority(System.nanoTime(), "ROLE_USER"));
//        authorityService.create(new Authority(System.nanoTime(), "ROLE_PREMIUM_USER"));
    }

    private void generateUsers() {

        if (userRepository.findByUsername("admin").isEmpty()) {
            var user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("secret"));
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authorityService.findByRoleName("ROLE_ADMIN"));
            user.setAuthorities(authorities);
            userRepository.save(user);
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            var user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("secret"));
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authorityService.findByRoleName("ROLE_USER"));
            user.setAuthorities(authorities);
            userRepository.save(user);
        }
    }

    @GetMapping("/{username}")
    public String userProfile(Model model, @PathVariable(value = "username") String username) {
        model.addAttribute("pageTitle", "@" + username + " - instagram_clone photos and videos");
        return "profile";
    }

    @GetMapping("/accounts/edit")
    public String editProfile(Model model) {
        model.addAttribute("pageTitle", "Edit Profile");
        return "accounts/edit";
    }

    @PostMapping("/edit_profile")
    public String editProfile_POST(Model model, @RequestParam(name = "image") MultipartFile file, HttpSession session) {
        String path = session.getServletContext().getRealPath("/");
        String filename = file.getOriginalFilename();

        System.out.println(path + " " + filename);
        try {
            byte[] barr = file.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path + "/" + filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

        model.addAttribute("filename", path + "/" + filename);
        return "profile/edit";
    }

}
