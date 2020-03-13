package com.jamilxt.instagram_clone.repositories;

import com.jamilxt.instagram_clone.model.Comment;
import com.jamilxt.instagram_clone.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    int countCommentsByPost(Post post);

    Page<Comment> findByPost(Post post, Pageable pageable);

}
