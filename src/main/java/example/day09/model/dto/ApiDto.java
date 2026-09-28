package example.day09.model.dto;

import example.day09.model.entity.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiDto {
    private Integer idx;

    private String subject;
    private String name;
    private String regdate;
    private String content;

    public static ApiDto entityToDto(ApiEntity apiEntity){
        return ApiDto.builder().subject(apiEntity.getSubject())
                .idx(apiEntity.getIdx())
                .name(apiEntity.getName())
                .regdate(apiEntity.getRegdate())
                .content(apiEntity.getContent()).build();
    }

    public ApiEntity dtoToEntity(){
        return ApiEntity.builder()
                .subject(this.subject)
                .name(this.name)
                .regdate(LocalDateTime.now().toString())
                .content(this.content).build();
    }
}
