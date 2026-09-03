package example.day05;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "test")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "name", nullable = true, length = 100, unique = true) // 제약조건설정
    private String name; // 이름

    @Column(columnDefinition = "varchar(100) not null default '제품설명'")
    private String descri; // 설명

    @Column(insertable = true , updatable = true)
    private Integer price; // 가격
    // + 제품 등록일 ++ 제품 수정일 -> BaseTime 상속
}

/*
* @Column(name = "필드명") : 생략시 자동으로 멤버변수명 지정
* @Column(nullable = true/false) : not null
* @Column(length = "문자열길이~255") : 최대길이 255
* @Column(unique = true/false) : 중복 불가능/가능
* @Column(columnDefinition = " SQL문 ") : SQL문 직접 입력
* @Column(insertable = true , updatable = true)
*/
