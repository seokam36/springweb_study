package example.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Exam3 {
    public static void main(String[] args) {
        // 메소드 레퍼런스 : 람다식이 단순히 기존메소드만 호출하는 경우 간결하게 표현하는 방법
        // 1.
        Function<String , Integer> function1 = (x) -> {return Integer.parseInt(x);};
        Function<String , Integer> function2 = Integer::parseInt;
        System.out.println(function2.apply("10")); // "10" -> 10

        // 2.
        List<String> names = List.of("유재석","강호동","신동엽","서장훈");
        names.stream().forEach(System.out::println);

        // 3. 이름들의 글자수 출력
        // 스트림 방식
        names.stream().map((name) -> {return name.length();}).forEach((result) -> System.out.println(result));
        // 메소드 레퍼러느 방식 , 메소드뒤에 () 사용 X , 내부적으로 메소드 호출하는 구조
        names.stream().map(String::length).forEach(System.out::println);

        // 4. names 리스트내 문자열 각각 대입하여 Student 객체 만들기
        // 스트림 방식
        List<Student> list = names.stream().map((name) -> {
            return new Student(name);
        }).toList();
        // 메소드 레퍼런스
        List<Student> list2 = names.stream().map(Student::new).toList();

        /*
        * 1. 클래스명::static메소드명
        * 2. 인스턴스명::메소드명
        * 3. 클래스명::new
        *
        * JPA 서비스 구조 : entity --> dto 변환
        * List<MemberDto> list = entityList.stream().map((entity) -> {return MemberDto.entityToDto(entity);}).toList();
        * List<MemberDto> list = entityList.stream().map(MemberDto::entityToDto).toList();
        *
        */
    }
}

class Student{
    private String name;

    public Student(String name){
        this.name = name;
    }
}


