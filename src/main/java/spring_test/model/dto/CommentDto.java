package spring_test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_test.model.entity.CommentEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class CommentDto {
    private Integer id;

    private String author;
    private String content;
    private String password;
    private Integer boardId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentEntity dtoToEntity(){
        return CommentEntity.builder()
                .author(this.author)
                .content(this.content)
                .password(this.password)
                .build();
    }

    public static CommentDto entityToDto(CommentEntity commentEntity){
        return CommentDto.builder()
                .id(commentEntity.getId())
                .author(commentEntity.getAuthor())
                .content(commentEntity.getContent())
                .password(commentEntity.getPassword())
                .createdAt(commentEntity.getCreatedAt())
                .updatedAt(commentEntity.getUpdatedAt()).build();
    }
}
