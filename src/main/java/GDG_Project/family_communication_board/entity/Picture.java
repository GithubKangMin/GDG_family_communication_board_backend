package GDG_Project.family_communication_board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "picture") // 테이블 이름을 명시적으로 설정
@Getter
@Setter
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    @Column(name = "picture_name", nullable = false, unique = true) // 컬럼 설정
    private String name;

    @Column(name = "upload_date", nullable = false)
    private int uploadDate;// 날짜 타입 사용

    // 사용자 정의 생성자
    public Picture(String name, int uploadDate) {
        this.name = name;
        this.uploadDate = uploadDate;
    }
}
