package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.entity.Comment;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPictureName(String pictureName);

    @Modifying
    @Transactional
    void deleteByPictureNameAndContent(String pictureName, String content);

    @Modifying
    @Transactional
    void deleteByPictureName(String pictureName);
}