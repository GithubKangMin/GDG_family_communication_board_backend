package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Comment;
import GDG_Project.family_communication_board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void addComment(Comment comment) {
        commentRepository.addComment(comment);
    }

    public void deleteComment(String pictureName, String content) {
        commentRepository.deleteComment(pictureName, content);
    }

    public List<Comment> getCommentsByPictureName(String pictureName) {
        return commentRepository.findCommentsByPictureName(pictureName);
    }

    @Transactional
    public void deleteCommentsByPictureName(String pictureName) {
        commentRepository.deleteCommentsByPictureName(pictureName);
        commentRepository.deletePictureByPictureName(pictureName);
    }
}
