package GDG_Project.family_communication_board.controller;

import GDG_Project.family_communication_board.entity.Comment;
import GDG_Project.family_communication_board.entity.Picture;
import GDG_Project.family_communication_board.service.CommentService;
import GDG_Project.family_communication_board.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @Autowired
    private CommentService commentService;



    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";  // upload.html 파일을 반환
    }

    @GetMapping("/comment")
    public String commentPage() {
        return "comment";  // upload.html 파일을 반환
    }

    @PostMapping("/upload")
    public String uploadPicture(@RequestParam("chooseFile") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                log.info("Attempting to upload file: {}", file.getOriginalFilename());
                pictureService.uploadPicture(file);
                log.info ("successfully uploaded file");
            } catch (IOException e) {
                log.error("Fail to upload picture", e);
                return "파일 업로드에 실패했습니다.";
            }
        }
        log.info("success to upload picture");
        return "redirect:/comment";
    }

    @PostMapping("/comment")
    @ResponseBody
    public List<Picture> getAllPictures() {
        List<Picture> pictures = pictureService.findAllPicture();
        log.info("사진 결과: {}", pictures);
        return pictures;  // Return JSON data directly
    }


    // 파일 업로드 동시에 읽어오기
    @GetMapping("/uploads/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get("src/main/resources/static/uploads").resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                log.warn("Could not read file: {}", filename);
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            log.error("Error serving file: {}", filename, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/comments")
    @ResponseBody
    public List<Comment> getCommentsByPictureName(@RequestParam String pictureName) {
        return commentService.getCommentsByPictureName(pictureName);
    }

    @PostMapping("/addComment")
    @ResponseBody
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/deleteComment")
    @ResponseBody
    public ResponseEntity<String> deleteComment(@RequestParam String pictureName, @RequestParam String content) {

        log.info("delete pictureName: {}, content: {}", pictureName, content);
        commentService.deleteComment(pictureName, content);
        return ResponseEntity.ok("Comment deleted successfully");
    }


}
