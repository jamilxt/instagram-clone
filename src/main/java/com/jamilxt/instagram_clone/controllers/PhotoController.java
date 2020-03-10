package com.jamilxt.instagram_clone.controllers;

import com.jamilxt.instagram_clone.dtos.PhotoDto;
import com.jamilxt.instagram_clone.model.Photo;
import com.jamilxt.instagram_clone.service.PhotoService;
import com.jamilxt.instagram_clone.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class PhotoController {

    @Autowired
    ServletContext context;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/photo/show-all")
//    @ResponseBody
    public String photo_showAll(Model model,
                                     @RequestParam(value = "caption") Optional<String> caption,
                                     @RequestParam(value = "page") Optional<Integer> page,
                                     @RequestParam(value = "sortBy") Optional<String> sortBy) {
        model.addAttribute("message", "Showing all photos");
        model.addAttribute("pageTitle", "Photo List");
        model.addAttribute("photos", photoService.showAll(caption, page, sortBy));
        return "photo/show-all";
//        return photoService.showAll(caption, page, sortBy);
    }

    @GetMapping("/photo/add")
    public String photo_add(Model model) {
        model.addAttribute("pageTitle", "Add Photo");
        model.addAttribute("photo", new PhotoDto());
        return "photo/add";
    }

    @PostMapping("/photo/add")
    public String photo_add_POST(@ModelAttribute("photo") Photo photo, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
                Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
                Files.write(path, bytes);
                photo.setUrl(file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }


        var photoDto = new PhotoDto();
        BeanUtils.copyProperties(photo, photoDto);
        photoService.save(photoDto);
        return "redirect:/photo/show-all";
    }

}
