package GDG_Project.family_communication_board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Picture {

    private String name;
    private int uploadDate;
    private Long id = 0L;

    public Picture(String name, int uploadDate) {
        this.name = name;
        this.id++;
        this.uploadDate = uploadDate;
    }
}