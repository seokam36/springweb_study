package spring_practice4.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_practice4.Dto.StudentDto;
import spring_practice4.Service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public boolean save(@RequestBody StudentDto studentDto){
        return studentService.save(studentDto);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Integer studentId){
        return studentService.delete(studentId);
    }
}
