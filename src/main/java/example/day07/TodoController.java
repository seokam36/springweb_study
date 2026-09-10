package example.day07;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/day07/todo")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public List<Map<String, Object>> find(@RequestParam String title, @RequestParam String content){
        return todoRepository.myQuery3(title, content);
    }
}
