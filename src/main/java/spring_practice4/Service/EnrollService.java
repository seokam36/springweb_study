package spring_practice4.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_practice4.Dto.EnrollDto;
import spring_practice4.Entity.CourseEntity;
import spring_practice4.Entity.EnrollEntity;
import spring_practice4.Entity.StudentEntity;
import spring_practice4.repository.CourseRepository;
import spring_practice4.repository.EnrollRepository;
import spring_practice4.repository.StudentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollService {
    private final EnrollRepository enrollRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public boolean save(EnrollDto enrollDto) {
        EnrollEntity enrollEntity = enrollDto.dtoToEntity();
        // ** DTO내 FK 값을 entity로 변환 **
        Optional<CourseEntity> courseRepositoryById = courseRepository.findById(enrollDto.getCourseId());
        Optional<StudentEntity> studentRepositoryById = studentRepository.findById(enrollDto.getStudentId());
        if (courseRepositoryById.isPresent() && studentRepositoryById.isPresent()) {
            CourseEntity courseEntity = courseRepositoryById.get();
            enrollEntity.setCourseEntity(courseEntity);
            StudentEntity studentEntity = studentRepositoryById.get();
            enrollEntity.setStudentEntity(studentEntity);
            enrollRepository.save(enrollEntity);
            return true;
        }
        return false;
    }

    public EnrollDto findById(Integer enrollId) {
        // Optional<> 클래스는 null 예외검사 메소드 제공, isPresent() 있으면true/false, orElse(없을때자료)
        EnrollEntity enrollEntity = enrollRepository.findById(enrollId).orElse(null);
        return EnrollDto.entityToDto(enrollEntity);

    }
}
