package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Comment;
import GDG_Project.family_communication_board.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void addComment(Comment comment) {
        commentRepository.save(comment); // save 메서드로 저장
    }

    @Transactional
    public void deleteComment(String pictureName, String content) {
        commentRepository.deleteByPictureNameAndContent(pictureName, content);
    }

    public List<Comment> getCommentsByPictureName(String pictureName) {
        return commentRepository.findByPictureName(pictureName);
    }

    @Transactional
    public void deleteCommentsByPictureName(String pictureName) {
        log.info("Deleting comments for pictureName: {}", pictureName);
        commentRepository.deleteByPictureName(pictureName);
        log.info("Comments deleted successfully");
    }
}

