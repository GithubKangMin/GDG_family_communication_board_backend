package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Comment;
import GDG_Project.family_communication_board.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void saveComment(Long pictureId, String text) {
        Comment comment = new Comment(pictureId, text, LocalDateTime.now());
        commentRepository.saveComment(comment);
        log.info("Saved comment: {}", comment);
    }

    public List<Comment> getCommentsByPictureId(Long pictureId) {
        return commentRepository.findCommentsByPictureId(pictureId);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteComment(id);
        log.info("Deleted comment with id: {}", id);
    }

    public void updateComment(Long id, String newText) {
        commentRepository.updateComment(id, newText);
        log.info("Updated comment with id: {}, newText: {}", id, newText);
    }
}
