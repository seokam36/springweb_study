package example.day02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 : 프레임워크 , 다양한 도구와 틀 제공 받음
// @어노테이션 : 코드에 추가적인 설명과 의미 부여할 때 사용
// 1.@SpringBootApplication : 1) 내장 톰캣 세팅 , 2) 서블릿(controller) 자동 등록

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // 2. Spring 실행
        // SpringApplication.run(현재클래스명.class);
        // 클래스.class : 클래스 메타정보(멤버변수, 생성자, 메소드) 반환
        // SpringApplication.run( springboot정보 );
        SpringApplication.run(AppStart.class);
    }
}
