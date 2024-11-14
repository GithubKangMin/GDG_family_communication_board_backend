package GDG_Project.family_communication_board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private Long id;
    private String pictureName;
    private String content;

    public Comment(String pictureName, String content) {
        this.pictureName = pictureName;
        this.content = content;
    }
}
