package example.day13;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate; // 프론트에 전달할 작성일자

    // 파일 업로드( 파일은 문자가 아닌 바이트 이므로 특정한 인터페이스 ) String X
    private MultipartFile file; // 업로드용, 등록용
    // private List<MultipartFile> fileList; 첨부파일 여러개
    // 파일 이름 (업로드된 파일명)
    private String fileName; // 출력용


    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
    public static BoardDto fromEntity(BoardEntity entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .fileName(entity.getFileName())
                .createDate(entity.getCreateDate())
                .build();
    }
}