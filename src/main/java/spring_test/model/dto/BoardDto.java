package spring_test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_test.model.entity.BoardEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class BoardDto {
    private Integer id;

    private String author;
    private String content;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();

    public BoardEntity dtoToEntity(){
        return BoardEntity.builder()
                .author(this.author)
                .content(this.content)
                .password(this.password).build();
    }

    public static BoardDto entityToDto(BoardEntity boardEntity){
        return BoardDto.builder()
                .id(boardEntity.getId())
                .author(boardEntity.getAuthor())
                .content(boardEntity.getContent())
                .password(boardEntity.getPassword())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt()).build();
    }
}
