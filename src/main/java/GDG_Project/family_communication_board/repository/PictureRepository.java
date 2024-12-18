package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.entity.Picture;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    // pictureName으로 사진 찾기
    Picture findByName(String name);

    // pictureName으로 사진 삭제
    @Modifying
    @Transactional
    void deleteByName(String name);

}

/*@Repository
@Mapper
public interface PictureRepository {

    @Insert("INSERT INTO picture (picture_name, upload_date) VALUES (#{name}, #{uploadDate})")
    void uploadPicture(Picture picture);

    @Select("SELECT picture_name AS name, upload_date AS uploadDate FROM picture")
    List<Picture> findAllPicture();

    @Select("SELECT id, picture_name AS name, upload_date AS uploadDate FROM picture WHERE picture_name = #{name}")
    Picture findPictureById(Long id);
}*/
