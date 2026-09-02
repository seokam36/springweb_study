package spring_practice1;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("")
    public boolean testWriter(@RequestBody TestDto testDto){
        System.out.println("TestController.testWriter");
        return true;
    }

    @GetMapping("")
    public ArrayList<TestDto> testPrint(){
        System.out.println("TestController.testPrint");
        ArrayList<TestDto> testDtos = new ArrayList<>();
        testDtos.add(new TestDto(1,"ddd","ddd"));
        return testDtos;
    }

    @GetMapping ("/detail")
    public TestDto testDetail(@RequestParam int no){
        System.out.println("TestController.testDetail");
        return new TestDto(1, "aaa", "aaa");
    }

    @DeleteMapping("")
    public boolean testDelete(@RequestParam int no){
        System.out.println("TestController.testDelete");
        return true;
    }

    @PutMapping("")
    public boolean testPut(@RequestBody TestDto testDto){
        return true;
    }
}
