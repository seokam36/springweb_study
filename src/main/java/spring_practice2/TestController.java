package spring_practice2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService service;

    @PostMapping("")
    public boolean save(@RequestBody TestEntity testEntity){
        return service.save(testEntity);
    }

    @GetMapping("")
    public List<TestEntity> findAll(){
        return service.findAll();
    }

    @GetMapping ("/detail")
    public TestEntity findById(@RequestParam int no){
        return service.findById(no);
    }

    @DeleteMapping("")
    public boolean delete(@RequestParam int no){
        return service.delete(no);
    }

    @PutMapping("")
    public boolean update(@RequestBody TestEntity testEntity){
        return service.update(testEntity);
    }
}
