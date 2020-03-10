package com.jamilxt.instagram_clone.service;

import com.jamilxt.instagram_clone.dtos.PhotoDto;
import com.jamilxt.instagram_clone.model.Photo;
import com.jamilxt.instagram_clone.repositories.PhotoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public Page<Photo> showAll(@RequestParam Optional<String> caption,
                               @RequestParam Optional<Integer> page,
                               @RequestParam Optional<String> sortBy) {
//        Pageable pageWithElements;
//        if (sort.equals("NA")) {
//            pageWithElements = PageRequest.of(pageIndex, rows, Sort.by("countryName").ascending());
//        } else {
//            pageWithElements = PageRequest.of(pageIndex, rows, Sort.by("countryName").descending());
//        }
        return photoRepository.findByCaption(caption.orElse("_"),
                PageRequest.of(page.orElse(0), 10,
                        Sort.Direction.DESC, sortBy.orElse("id")));
    }

    public void save(PhotoDto photoDto) {

        var photoEntity = new Photo();
        BeanUtils.copyProperties(photoDto, photoEntity);
        photoRepository.save(photoEntity);

    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id); // will be converted to soft delete
    }
}
