package spring_practice5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_practice5.entity.CommentEntity;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CommentDto {
    private Integer commentId;

    private String author;
    private String password;
    private String content;
    private Integer boardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentEntity dtoToEntity(){
        return CommentEntity.builder()
                .author(this.author)
                .password(this.password)
                .content(this.content).build();
    }

    public static CommentDto entityToDto(CommentEntity commentEntity){
        return CommentDto.builder()
                .commentId(commentEntity.getCommentId())
                .author(commentEntity.getAuthor())
                .password(commentEntity.getPassword())
                .content(commentEntity.getContent())
                .boardId(commentEntity.getBoardEntity().getBoardId())
                .createdAt(commentEntity.getCreatedAt())
                .createdAt(commentEntity.getUpdatedAt()).build();
    }
}
