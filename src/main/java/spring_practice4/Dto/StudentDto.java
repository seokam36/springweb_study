package spring_practice4.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_practice4.Entity.StudentEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class StudentDto {
    private Integer studentId;
    private String studentName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // 학생 출력 용도
    public static StudentDto entityToDto(StudentEntity studentEntity){
        return StudentDto.builder().
                studentId(studentEntity.getStudentId()).
                studentName(studentEntity.getStudentName())
                .createdDate(studentEntity.getCreatedDate())
                .updatedDate(studentEntity.getUpdatedDate()).build();
    }

    // 학생 등록 용도
    public StudentEntity dtoToEntity(){
        return StudentEntity.builder()
                .studentName(this.studentName).build();
    }
}
