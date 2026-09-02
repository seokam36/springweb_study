package example.day03;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/day03") // 클래스내 메소드들의 공통URL 정의
@RestController // @Component + @Controller + @ResponseBody
// 활용 : HTML -> @Controller , JSON(값) -> @RestController
public class RestController2 {
    @GetMapping("/task5")
    public String task5(){
        return "서버에서 응답하는 메시지";
    }

    // ---------- 요청 매개변수 ----------- //
    // @RequestParam -> 요청 content-type이 쿼리스트링의 매개변수 매핑/여결 , 생략가능
    @GetMapping ("/task6")
    public int task6(@RequestParam String name,@RequestParam int age){
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return 5;
    }

    @GetMapping("/task7")
    public int task7(String name, // @RequestParam 생략가능
                     @RequestParam(name = "age")int age, // @RequestParam(name = "매핑할매개변수명")
                     @RequestParam(required = false, defaultValue = "10") int count )
    // @RequestParam(required="필수여부" , defaultValue = "기본값")
    {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("count = " + count);
        return 1;
    } // @RequestParam(required="필수여부")

    @DeleteMapping("/task8")
    public int task8(@RequestParam Map<String, Object> map){
        System.out.println(map);
        return 1;
    }

    @DeleteMapping("/task9")
    public int task9(@ModelAttribute ExamDto examDto){ // dto는 @ModelAttribute 사용, 생략가능
        System.out.println(examDto);
        return 1;
    }

    @GetMapping("/task10/{name}/{age}") // 경로구분자 @PathVariable 사용, 매개변수 이름 같으면 생략가능
    public int task10(@PathVariable(name = "name") String name,
                      @PathVariable(name = "age") int age){
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return 1;
    }

    @PostMapping("/task11")
    public int task11(@RequestBody ExamDto examDto){
        System.out.println("examDto = " + examDto);
        return 1;
    }
}
// 1. @RequestParam : 쿼리스트링 또는 content-type : 'application/x-www-form-urlencoded
// 2. @ModelAttribute(생략가능) : Dto 매핑
// 3. @PathVariable : 경로구분자
// 4. @RequestBody : content-type: 'application/json'
// -> POST / PUT 메소드만 body(본문-경로상의 노출이없음) 지원

/*
자바
1. @Override: 상위 클래스/인터페이스의 메서드를 재정의함을 명시 및 검증
2. @Retention: 어노테이션의 유지 범위(SOURCE, CLASS, RUNTIME) 지정
3. @Target: 어노테이션이 적용될 대상(TYPE, METHOD, FIELD 등) 지정

롬복
1. @NoArgsConstructor: 기본 생성자 자동 생성
2. @AllArgsConstructor: 모든 필드를 포함하는 생성자 자동 생성
3. @Getter: Getter 메서드 자동 생성
4. @Setter: Setter 메서드 자동 생성
5. @ToString: toString() 메서드 자동 생성
6. @RequiredArgsConstructor: final 필드만 포함하는 생성자 자동 생성
7. @Builder: 빌더 패턴 방식의 객체 생성 메서드 자동 생성

스프링
1. @SpringBootApplication: 톰캣 자동 구성, 컴포넌트 스캔, 설정 기능을 포함한 시작점 지정
2. @Component: 스프링 빈(Bean, 객체)으로 등록
3. @Autowired: 스프링 빈(객체) 의존성 자동 주입

스프링 HTTP
1. @Controller: Spring MVC 컨트롤러 등록 (프론트엔드파일 반환용)
2. @RestController: RESTful 웹 서비스 컨트롤러 등록 (@Controller + @ResponseBody)
3. @ResponseBody: 반환 객체를 HTTP 응답 본문(JSON/XML/Text)으로 전송
4. @RequestMapping: 클래스 또는 메서드 레벨에서 공통 URI 경로 및 HTTP 속성을 정의
5. @GetMapping: HTTP GET 요청 전용 매핑
6. @PostMapping: HTTP POST 요청 전용 매핑
7. @PutMapping: HTTP PUT 요청 전용 매핑
8. @DeleteMapping: HTTP DELETE 요청 전용 매핑
9. @RequestParam: 쿼리 스트링 또는 폼 파라미터(application/x-www-form-urlencoded) 값 추출
10. @ModelAttribute: 요청 파라미터를 객체(DTO) 필드에 저장
11. @PathVariable: URI 경로 자체에 포함된 식별자 값 추출
12. @RequestBody: HTTP body(JSON, XML 등, POST/PUT에서 주로 사용) 데이터를 Java 객체로 저장
*/