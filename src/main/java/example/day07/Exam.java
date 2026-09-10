package example.day07;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exam {
    public static void main(String[] args) {
        // 1. 구현체 : 추상메소드(인터페이스) 구현한 객체
        // 2. 익명구현체 : 추상메소드 구현한 클래스 없이 직접 구현
        Calculator calc = new Calculator() {
            @Override
            public int plus(int x, int y) {
                return x+y;
            }
        };
        int result = calc.plus(1, 2);

        // 3. 람다 표현식
        Calculator cal2 = (x, y) -> {return x + y;};
        int result2 = cal2.plus(1, 2);

        // 람다 표현식 사용하는 인터페이스들
        // ** 제네릭 => 클래스/인터페이스 안에서 사용할 타입
        // Function< 인수타입, 반환타입 > , apply( 인수 ) , 매개변수o 반환o
        Function<Integer, Integer> function = (x) -> {return x*2;};
        System.out.println(function.apply(3));

        // Supplier< 반환타입 > , .get() , 매개변수x 반환o
        Supplier<Integer> supplier = ( ) -> {return 2;};
        System.out.println(supplier.get());

        // Consumer< 인수타입 > , accept(인수) , 매개변수o 반환x
        Consumer<String> consumer = (str) -> {System.out.println(str);};
        consumer.accept("유재석");

        // Predicate< 인수타입 > , test(인수) , 매개변수o 반환(boolean)
        Predicate<Integer> predicate = (x) -> {return  x%2==0;};
        boolean test = predicate.test(3);

        // 활용처 : 위 4가지 직ㅇ접적인 사용이 아닌 API(남이 만든 클래스)에서 활용
        // forEach, filter, map 등등

    }
}

interface Calculator{
    int plus(int x, int y);
}
