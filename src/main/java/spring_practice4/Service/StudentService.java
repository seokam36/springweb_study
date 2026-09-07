package spring_practice4.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_practice4.Dto.StudentDto;
import spring_practice4.Entity.StudentEntity;
import spring_practice4.repository.StudentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    public boolean save(StudentDto studentDto) {
        StudentEntity studentEntity = studentDto.dtoToEntity();
        studentRepository.save(studentEntity);
        return true;
    }

    public boolean delete(Integer studentId) {
        Optional<StudentEntity> studentRepositoryById = studentRepository.findById(studentId);
        if (studentRepositoryById.isPresent()){
            StudentEntity studentEntity = studentRepositoryById.get();
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }
}
