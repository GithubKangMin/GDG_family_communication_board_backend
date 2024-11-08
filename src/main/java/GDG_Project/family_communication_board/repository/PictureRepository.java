package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.model.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface PictureRepository {

    @Insert("INSERT INTO picture (picture_name, upload_date) VALUES (#{name}, #{uploadDate})")
    void uploadPicture(Picture picture);

    @Select("SELECT picture_name AS name, upload_date AS uploadDate FROM picture")
    List<Picture> findAllPicture();

    @Select("SELECT id, picture_name AS name, upload_date AS uploadDate FROM picture WHERE picture_name = #{name}")
    Picture findPictureById(Long id);

}
