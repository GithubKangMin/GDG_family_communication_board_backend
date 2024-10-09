package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.entity.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureRepository {

    @Insert("Insert into picture (picture_name, upload_date) VALUES  ({#{pictureName}, #{uploadDate})")
    void uploadPicture(Picture picture);

    @Select("SELECT * From picture")
    List<Picture> findAllPicture();
}
