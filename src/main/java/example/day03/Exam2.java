package example.day03;

import lombok.*;

public class Exam2 {
    public static void main(String[] args) {
        Student s1 = new Student();// 1. @NoArgsConstructor
        Student s2 = new Student("주석암", 100, 100);// 2.@AllArgsConstructor
        s1.setKor(100); // 3. @Setter
        System.out.println(s2.getKor()); // 4. @Getter
        System.out.println(s2.toString()); // 5. @ToString

        // 6. 빌더 패턴을 이용한 객체 생성 , 장점 : 순서무관, 선택적대입, 유연한 객체생성
        Student s3 = Student.builder() // 빌더 패턴 시작
                .kor(100).name("주석암").math(100)
                .build(); // 빌더 패턴 끝
    }
}

@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 전체 매개변수 있는 생성자
//@RequiredArgsConstructor // final 멤버변수의 생성자
@Getter
@Setter
@ToString
@Data // getter, setter, requiredConstructor, toString, EqualsAndHashCode  묶음
@EqualsAndHashCode // 객체내 멤버변수의 값 모두 비교 테스트
@Builder // 빌더 패턴 지원
class Student{
    private String name;
    private int kor;
    private int math;
}
