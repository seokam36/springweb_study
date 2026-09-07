package spring_practice4.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_practice4.Dto.CourseDto;
import spring_practice4.Service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public boolean save(@RequestBody CourseDto courseDto){
        return courseService.save(courseDto);
    }

    @GetMapping
    public List<CourseDto> findAll(){
        return courseService.findAll();
    }
}
