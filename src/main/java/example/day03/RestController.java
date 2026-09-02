package example.day03;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@Component // [싱글톤 대신] 스프링 컨테이너에 해당 클래스의 객체 등록
@Controller// [서블릿 대신] HTTP 통신 지원하는 서블릿 제공 + @Component
public class RestController {
    @GetMapping (value = "/day03/task1")
    @ResponseBody // http 응답 JSON 자동 타입변환
    public int task1(){
        System.out.println("RestController.task1");
        return 10;
    }

    // 2.
    @GetMapping("/day03/task2")
    @ResponseBody
    public String task2(){
        System.out.println("RestController.task2");
        return "hi";
    }

    // 3.
    @GetMapping("/day03/task3")
    @ResponseBody
    public Map<String , Object> task3(){
        Map<String, Object> map = new HashMap<>();
        map.put("유재석",100);
        map.put("유재석",90);
        return map;
    }

    // 4.
    @GetMapping("/day03/task4")
    @ResponseBody
    public ExamDto task4(){
        ExamDto examDto = new ExamDto();
        examDto.setName("주석암");
        examDto.setAge(999);;
        return examDto;
    }
}

@Data
class ExamDto{
    String name;
    int age;
}