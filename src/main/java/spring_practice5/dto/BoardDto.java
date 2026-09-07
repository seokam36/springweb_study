package spring_practice5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_practice5.entity.BoardEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class BoardDto {
    private Integer boardId;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();

    public BoardEntity dtoToEntity(){
        return BoardEntity.builder()
                .author(this.author)
                .password(this.password)
                .content(this.content).build();
    }

    public static BoardDto entityToDto(BoardEntity boardEntity){
        return BoardDto.builder()
                .boardId(boardEntity.getBoardId())
                .author(boardEntity.getAuthor())
                .password(boardEntity.getPassword())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt()).build();
    }
}
