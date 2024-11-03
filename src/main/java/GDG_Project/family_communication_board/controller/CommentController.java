package GDG_Project.family_communication_board.controller;

import GDG_Project.family_communication_board.entity.Comment;
import GDG_Project.family_communication_board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 댓글 추가
    @PostMapping("/add")
    public String addComment(@RequestParam Long pictureId, @RequestParam String text) {
        commentService.saveComment(pictureId, text);
        return "redirect:/picture/comment";
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    // 댓글 수정
    @PutMapping("/update/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newText = body.get("text");
        commentService.updateComment(id, newText);
    }
}
