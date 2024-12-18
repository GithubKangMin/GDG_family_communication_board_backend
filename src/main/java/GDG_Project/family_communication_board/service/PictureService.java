package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Picture;
import GDG_Project.family_communication_board.repository.CommentRepository;
import GDG_Project.family_communication_board.repository.PictureRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.time.LocalDate;

@Service
@Slf4j
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CommentRepository commentRepository;

    private final String uploadDirectory = Paths.get("src/main/resources/static/uploads/").toAbsolutePath().toString();

    public void uploadPicture(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        // 파일 저장
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, filename);
        Files.createDirectories(path.getParent());
        Files.write(path, bytes);

        // 엔티티 저장
        Picture picture = new Picture(filename, LocalDate.now().getYear() % 100 * 10000 + LocalDate.now().getDayOfYear());
        pictureRepository.save(picture); // save 메서드 사용
        log.info("Saved picture to database: {}", picture);
    }

    public Picture findPictureById(Long pictureId) {
        return pictureRepository.findById(pictureId).orElse(null);
    }

    public List<Picture> findAllPicture() {
        return pictureRepository.findAll();
    }

    @Transactional
    public void deletePictureAndComments(String pictureName) {
        // 댓글 먼저 삭제
        log.info("Deleting comments : {}", pictureName);
        commentRepository.deleteByPictureName(pictureName); // 댓글 삭제
        log.info("Comments deleted successfully");

        // 사진 삭제
        log.info("Deleting picture : {}", pictureName);
        pictureRepository.deleteByName(pictureName); // 사진 삭제
        log.info("Picture deleted successfully");
    }

}
