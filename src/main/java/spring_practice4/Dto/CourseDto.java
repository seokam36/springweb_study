package spring_practice4.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_practice4.Entity.CourseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // + 학생목록
    @Builder.Default
    private List<StudentDto> studentDtoList = new ArrayList<>();

    // 과정 출력 용도
    public static CourseDto entityToDto(CourseEntity courseEntity){
        return CourseDto.builder()
                .courseId(courseEntity.getCourseId())
                .courseName(courseEntity.getCourseName())
                .createdDate(courseEntity.getCreatedDate())
                // .studentDtoList( new ArrayList<>())
                //.studentDtoList( courseEntity.getEnrollEntityList().stream().map( enrollEntity -> { return  StudentDto.entityToDto( enrollEntity.getStudentEntity() ); }).toList() )
                .updatedDate(courseEntity.getUpdatedDate()).build();
        // 학생 목록은 서비스에서
    }

    // 과정 등록 용도
    public CourseEntity dtoToEntity(){
        return CourseEntity.builder().
                courseName(this.getCourseName()).build();
    }
}
