package example.day06;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardEntity {
    @Id
    private Integer bno;
    private String bname;

    // 단방향 참조
    @ManyToOne // 일대다 관계
    @JoinColumn(name = "cno") // FK이름 지정, 주로 PK와 동일하게
    private CategoryEntity category;

    @OneToMany(mappedBy = "board")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyList = new ArrayList<>();
}
