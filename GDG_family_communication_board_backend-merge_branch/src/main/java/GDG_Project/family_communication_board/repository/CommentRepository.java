package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentRepository {

    @Insert("INSERT INTO comment (picture_name, content) VALUES (#{pictureName}, #{content})")
    void addComment(Comment comment);

    @Delete("DELETE FROM comment WHERE  content = #{content} AND picture_name = #{pictureName}")
    void deleteComment(@Param("pictureName") String pictureName, @Param("content") String content);

    @Select("SELECT * FROM comment WHERE picture_name = #{pictureName}")
    List<Comment> findCommentsByPictureName(String pictureName);

    @Delete("DELETE FROM comment WHERE picture_name = #{pictureName}")
    void deleteCommentsByPictureName(@Param("pictureName") String pictureName);

    @Delete("DELETE FROM picture WHERE picture_name = #{pictureName}")
    void deletePictureByPictureName(@Param("pictureName") String pictureName);
}
