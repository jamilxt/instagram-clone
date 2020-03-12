package com.jamilxt.instagram_clone.repositories;

import com.jamilxt.instagram_clone.model.Post;
import com.jamilxt.instagram_clone.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from tbl_post p where p.caption like %:keyword%", nativeQuery = true)
    Page<Post> findByCaption(@Param("keyword") String caption, Pageable pageable);

    Page<Post> findByUser(User username, Pageable pageable);

    int countPostsByUser(User user);

}
