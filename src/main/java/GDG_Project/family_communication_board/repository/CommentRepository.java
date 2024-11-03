package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentRepository {

    @Insert("INSERT INTO comment (picture_id, text, upload_date) VALUES (#{pictureId}, #{text}, #{uploadDate})")
    void saveComment(Comment comment);

    @Select("SELECT id, picture_id AS pictureId, text, upload_date AS uploadDate FROM comment WHERE picture_id = #{pictureId}")
    List<Comment> findCommentsByPictureId(Long pictureId);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    void deleteComment(Long id);

    @Update("UPDATE comment SET text = #{text} WHERE id = #{id}")
    void updateComment(@Param("id") Long id, @Param("text") String text);
}
