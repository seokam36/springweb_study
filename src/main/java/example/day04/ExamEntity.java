package example.day04;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // 엔티티 객체 등록
@Table (name = "exam") // 매핑할 DB테이블의 이름 정의, 생략시 클래스명으로 자동 매핑
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamEntity { // 엔티티는 반드시 1개 이상의 PK 필요
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 지원, mysql만 가능
    private Integer eno;

    private String ename;
}
