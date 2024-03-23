package org.example.englishforum.repository;

import org.example.englishforum.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {

}
