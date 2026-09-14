package spring_total.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_total.model.entity.ReviewEntity;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class ReviewDto {
    private Integer rno;
    private String reviewer;
    private String content;
    private Integer rating;
    private Integer bno;

    public ReviewEntity dtoToEntity(){
        return ReviewEntity.builder()
                .reviewer(this.reviewer)
                .content(this.content)
                .rating(this.rating)
                .build();
    }

    public static ReviewDto entityToDto( ReviewEntity entity ){
        return ReviewDto.builder()
                .reviewer(entity.getReviewer())
                .content(entity.getContent())
                .rating(entity.getRating())
                .build();
    }
}
