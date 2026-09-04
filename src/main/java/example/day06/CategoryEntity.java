package example.day06;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryEntity {
    @Id
    private Integer cno;
    private String cname;

    // 양방향 참조
    @OneToMany (mappedBy = "category") // 하나가 다수에게 참조 , mappedBy = "매핑할멤버변수"  자바(JPA)에서만 매핑
    @ToString.Exclude
    @Builder.Default // 만약 해당 entity가 빌더 패턴 사용시 초기값 그대로 사용
    private List<BoardEntity> boardList = new ArrayList<>();
}
