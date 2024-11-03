package GDG_Project.family_communication_board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private Long id;
    private Long pictureId;
    private String text;
    private LocalDateTime uploadDate;

    public Comment(Long pictureId, String text, LocalDateTime uploadDate) {
        this.pictureId = pictureId;
        this.text = text;
        this.uploadDate = uploadDate;
    }
}
