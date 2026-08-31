package example.day03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Exam1 {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.method1(); // 부모가 아닌 오버라이딩 된 메소드
        subClass.method2(); // 실행은 가능하지만 권장 X

        // 1. 클래스의 정보 반환
        Class<TestClass> clazz = TestClass.class;
        try {
            // 2. 특정 메소드 반환
            Method method = clazz.getMethod("method3");
            // 3. 특정 메소드의 어노테이션 확인
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            // 4. 특정 어노테이션의 속성 확인
            System.out.println(annotation.value());
            System.out.println(annotation.data());
            // 5. 동적 로딩
            TestClass testClass = clazz.getDeclaredConstructor().newInstance();
            method.invoke(testClass);
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

class SuperClass{
    void method1(){};
}

class SubClass extends SuperClass{
    @Override // [1] 재정의 어노테이션 : 컴파일중 해당 메소드 사용하는 방법 명시
    void method1() {
        super.method1();
    }

    @Deprecated // [2] 더이상 사용을 권장 안함 어노테션
    void method2(){};
}

// [3] 어노테이션 만들기 : @interface 어노테이션명{}
@Retention(RetentionPolicy.RUNTIME) // 실행중 유지
@Target(ElementType.METHOD) // 해당 어노테이션 사용처
@interface MyAnnotation{
    String value(); // 추상 메소드 형식의 어노테이션 속성
    int data() default 1; // 어노테이션 속성 , default 값
}

// [4] 어노테이션 주입 :
class TestClass{
    @MyAnnotation(value = "hi" , data = 10) // @내가만든어노테이션 (매개변수)
    public void method3(){
        System.out.println("method3");
    }
    @MyAnnotation(value = "hello")
    public void method4(){
        System.out.println("method4");
    }
}
