package example.day06;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reply")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReplyEntity {
    @Id
    private Integer rno;
    private String rname;

    //단반향 참조
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private BoardEntity board;
}
/*
* @ManyToOne ( cascade = 영속성제약조건, fetch = 불러오기 시기 )
* CascadeType.REMOVE = 만일 부모 엔티티가 삭제되면 자식 엔티티 같이 삭제
* CascadeType.MERGE = 만일 부모 엔티티가 수정되면 자식 엔티티 같이 수정
* CascadeType.DETACH = 만일 부모 엔티티가 영속해제하면 자식 엔티티 같이 해제
* .REFRESH = 부모엔티티 재호출(갱신)되면 자식 엔티티 같이 갱신
* .PERSIST = 부모엔티티 저장하면 자식 엔티티 같이 저장
* .ALL = 모두 사용
*
* FetchType.LAZY = 해당 엔티티 조회시 자식 엔티티 불러오지 않음 , 초기 로딩 빠름, 재사용성 느림, 필요한 정보만 불러옴
* FetchType.EAGER = 해당 엔티티 조회시 자식 엔티티 불러옴 , 기본값, 초기 로딩 느림, 재사용성 빠름, 불필요한 정보까지 불러옴
*/
