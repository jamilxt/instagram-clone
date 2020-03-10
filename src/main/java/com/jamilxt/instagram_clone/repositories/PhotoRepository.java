package com.jamilxt.instagram_clone.repositories;

import com.jamilxt.instagram_clone.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query(value = "select * from tbl_photo p where p.caption like %:keyword%", nativeQuery = true)
    Page<Photo> findByCaption(@Param("keyword") String caption, Pageable pageable);
}
