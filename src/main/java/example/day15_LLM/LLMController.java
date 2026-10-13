package example.day15_LLM;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class LLMController {
    private final LLMService llmService;

    @GetMapping("/chat1")
    public String chat1(@RequestParam String prompt){
        return llmService.chat1(prompt);
    }

    @GetMapping("/chat2")
    public String chat2(){
        return llmService.chat2();
    }

    @GetMapping("/chat3")
    public String chat3(@RequestParam String topic){
        return llmService.chat3(topic);
    }

    @GetMapping("/chat4")
    public String chat4(@RequestParam String userMessage){
        return llmService.chat4(userMessage);
    }
}
