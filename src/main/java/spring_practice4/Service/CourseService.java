package spring_practice4.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_practice4.Dto.CourseDto;
import spring_practice4.Dto.StudentDto;
import spring_practice4.Entity.CourseEntity;
import spring_practice4.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    public boolean save(CourseDto courseDto) {
        CourseEntity courseEntity = courseDto.dtoToEntity();
        CourseEntity saved = courseRepository.save(courseEntity);
        if (saved.getCourseId() > 0){
            return true;
        }
        return false;
    }

    public List<CourseDto> findAll() {
        List<CourseEntity> all = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();

        all.forEach((courseEntity -> {
            CourseDto courseDto = CourseDto.entityToDto(courseEntity);
            // ** 과정 DTO에 학생 목록 추가 **
            // 현재 과정(course) --> 수강기록(enroll)들을 반복해서 --> 수강기록 --> 학생
            courseEntity.getEnrollEntityList().forEach((enrollEntity -> {
                StudentDto studentDto = StudentDto.entityToDto(enrollEntity.getStudentEntity());
                courseDto.getStudentDtoList().add(studentDto);
            }));
            courseDtos.add(courseDto);
        }));
        return courseDtos;
    }
}
