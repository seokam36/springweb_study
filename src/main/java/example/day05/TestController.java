package example.day05;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService service;
    // 1. 전체 조회
    @GetMapping("/test")
    public List<TestDto> findAll(){
        return service.findAll();
    }

    // 2. 등록
    @PostMapping("/test")
    public boolean save(@RequestBody TestDto testDto){
        return service.save(testDto);
    }

    // 3. 수정
    @PutMapping("/test")
    public boolean update(@RequestBody TestDto testDto){
        return service.update(testDto);
    }
}
