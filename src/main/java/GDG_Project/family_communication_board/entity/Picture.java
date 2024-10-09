package GDG_Project.family_communication_board.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data // -> getter, setter
public class Picture {

    private String name;
    private LocalDateTime uploadDate;
    private Long id = 0L;

    public Picture(String name, LocalDateTime date) {
        this.name = name;
        this.id++;
        this.uploadDate = date;
    }
}

