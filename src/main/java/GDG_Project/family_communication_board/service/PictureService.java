package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Picture;
import GDG_Project.family_communication_board.repository.PictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    // 프로젝트 내에 저장
    private final String uploadDirectory = Paths.get("src/main/resources/static/uploads/").toAbsolutePath().toString();

    public void uploadPicture(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        // 파일 이름에 타임스탬프를 추가하여 유니크하게 저장
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, filename);
        Files.createDirectories(path.getParent());
        Files.write(path, bytes);

//        Picture picture = new Picture(file.getOriginalFilename(), Integer.parseInt(new SimpleDateFormat("yyMMdd").format(new Date())));
        Picture picture = new Picture(filename, Integer.parseInt(new SimpleDateFormat("yyMMdd").format(new Date())));
        pictureRepository.uploadPicture(picture);
        log.info("Saved picture to database: {}", picture);
    }

    public Picture findPictureById(Long pictureId) {
        return pictureRepository.findPictureById(pictureId);
    }

    public List<Picture> findAllPicture() {
        return pictureRepository.findAllPicture();
    }

}
