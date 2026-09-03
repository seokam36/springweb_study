package spring_practice3;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
[조건 1] 엔티티 설계 : “영화(Movie)” 정보를 저장할 엔티티 클래스를 생성한다.
각 필드의 역할과 데이터 타입을 적절히 설정한다.
@Entity, @Table, @Id, @GeneratedValue, @Column 등의 JPA 어노테이션을 활용한다.
항목을 포함할 것: 영화번호(movieid), 영화제목(title), 감독(director), 개봉일(releasedate), 평점(rating)
공통 상속 엔티티(BaseTime)를 적용하여 createdDate, updatedDate 자동 기록 기능을 포함한다.
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie")
public class MovieEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(length = 50)
    private String title;

    @Column(length = 30 )
    private String director;

    @Column(length = 50)
    private String releasedate;

    @Column(length = 50)
    private String rating;
}
