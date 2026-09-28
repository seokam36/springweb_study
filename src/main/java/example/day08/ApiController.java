package example.day08;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/test1")
    public Map<String, Object> test1(){
        return apiService.test1();
    }

    @GetMapping("/test2")
    public Map<String, Object> test2(){
        return apiService.test2();
    }

    @GetMapping("/test3")
    public List<Map<String , Object>> test3(){
        return apiService.test3();
    }

    @GetMapping(value = "/test4", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> test4(){
        return apiService.test4();
    }
}
