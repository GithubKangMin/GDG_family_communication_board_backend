package GDG_Project.family_communication_board.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class Picture {

    private String name;
    private int uploadDate;
    private Long id = 0L;

    public Picture(String name, int date) {
        this.name = name;
        this.id++;
        this.uploadDate = date;
    }
}

