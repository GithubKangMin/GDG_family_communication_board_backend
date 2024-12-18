package GDG_Project.family_communication_board.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
@Getter @Setter // Getter, Setter, 기본 생성자
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_name", nullable = false)
    private String pictureName;

    @Column(name = "content", nullable = false)
    private String content;
}
