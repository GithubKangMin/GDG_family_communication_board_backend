package GDG_Project.family_communication_board.controller;

import GDG_Project.family_communication_board.entity.Picture;
import GDG_Project.family_communication_board.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Autowired
    private PictureService pictureService;

    // 사진 업로드 요청 처리
    @PostMapping("/upload")
    public String uploadPicture(@RequestParam("chooseFile") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                log.info ("success to file upload");
                pictureService.uploadPicture(file);
            } catch (IOException e) {
                log.error("Fail to upload picture", e);
                return "파일 업로드에 실패했습니다.";
            }
        }
        log.error("success to upload picture");
        return "redirect:/comment";  // 파일 업로드 후 comment.html로 리다이렉트
    }

    // 모든 사진을 가져와서 comment.html에 표시
    @GetMapping("/comment")
    public String getAllPictures(Model model) {
        List<Picture> pictures = pictureService.findAllPicture();
        model.addAttribute("pictures", pictures);
        return "comment";  // comment.html에 사진 데이터 전달
    }
}
