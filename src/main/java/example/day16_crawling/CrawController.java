package example.day16_crawling;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrawController {
    private final CrawService crawService;

    @GetMapping("/craw1")
    public List<String> craw1(){
        return crawService.craw1();
    }

    @GetMapping("/craw2")
    public List<Map<String , Object>> craw2(){
        return crawService.craw2();
    }
}
