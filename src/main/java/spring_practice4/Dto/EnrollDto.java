package spring_practice4.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_practice4.Entity.EnrollEntity;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class EnrollDto {
    private Integer enrollId;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Integer courseId;
    private Integer studentId;

    // + 과정명, 학생명
    private String courseName;
    private String studentName;

    public static EnrollDto entityToDto(EnrollEntity enrollEntity){
        return EnrollDto.builder().enrollId(enrollEntity.getEnrollId()).
        status(enrollEntity.getStatus()).
        courseName(enrollEntity.getCourseEntity().getCourseName()).
        status(enrollEntity.getStudentEntity().getStudentName()).build();
    }

    public EnrollEntity dtoToEntity(){
        return EnrollEntity.builder().status(this.getStatus()).build();
        // 학생FK, 과정FK는 서비스에서
    }
}
