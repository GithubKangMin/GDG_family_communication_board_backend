package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Picture;
import GDG_Project.family_communication_board.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    private final String uploadDirectory = System.getProperty("user.home") + "\\Downloads\\";

    public void uploadPicture (MultipartFile file) throws IOException {

        byte [] bytes = file.getBytes();
        Path path = Paths.get(uploadDirectory + file.getOriginalFilename());
        Files.write(path, bytes);

        Picture picture = new Picture(file.getOriginalFilename(), LocalDateTime.now());
        pictureRepository.uploadPicture(picture);
    }

    public List<Picture> findAllPicture() {
        return pictureRepository.findAllPicture();
    }
}
