package spring_practice3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 [조건 3] DTO 설계 : 영화 정보를 전송하기 위한 DTO 클래스를 생성한다.
필드는 Entity와 동일하게 구성하되, 아래 메서드를 포함할 것.
createdDate, updatedDate 포함한다.
public MovieEntity toEntity() : DTO → Entity 변환
public static MovieDto from( ) : Entity → DTO 변환
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private Integer movieId;
    private String title;
    private String director;
    private String releasedate;
    private String rating;

    public MovieEntity toEntity(){
        return MovieEntity.builder().director(this.director).rating(this.rating).releasedate(this.releasedate)
                .title(this.title).build();
    }

    public static MovieDto toDto(MovieEntity movieEntity){
        return MovieDto.builder().movieId(movieEntity.getMovieId()).title(movieEntity.getTitle())
                .director(movieEntity.getDirector()).releasedate(movieEntity.getReleasedate())
                .rating(movieEntity.getRating()).build();
    }
}
