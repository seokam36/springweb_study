package example.day07;

import java.util.Comparator;
import java.util.List;

public class Exam2 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        // 1. 컬렉션 순회
        numbers.forEach(number -> {
            System.out.println(number);
        });

        // 2. 스트림 API , 리스트객체.stream().중간연산1().중간연산2().최종연산();
        // 1) stream().forEach() , return 없는 단순 순회 제공
        numbers.stream().forEach((number)->{
            System.out.println(number);
        });

        // 2) stream().map((순회변수명)->{return 값;}).toList() , return 제공
        List<Integer> list1 = numbers.stream().map((data) -> {
            return data;
        }).toList();
        System.out.println(list1);

        // 3) stream().filter((순회변수명) -> {return 조건식;}).최종연산()
        List<Integer> list2 = numbers.stream().filter((data) -> {
            return data % 2 == 0;
        }).toList();
        System.out.println(list2);

        // 4) stream().sorted().최종연산(); => 오름차순 정렬 , Comparator.reverseOrder() 내림차순 정렬
        List<Integer> list3 = numbers.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println(list3);

        // 5) stream().distinct().toList(); => 중복 제거
        //    stream().limit(개수); => 개수만큼 반환
        List<Integer> list4 = numbers.stream().distinct().limit(3).toList();
        System.out.println(list4);

        // 6) 중간연산은 여러번 가능, 최종연산으 한번만 가능
        List<Integer> list5 = numbers.stream().distinct()
                .filter((data) -> {return data%2==0;})
                .map((data) -> {return data;})
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
        System.out.println(list5);
        // * JPA에서 entity <--> dto 변환 과정

        // stream => 컬렉션 인스턴스를 흐름단위로 중간연산과 최종연산 제공 API
        // 메소드레퍼런슨API => 미리 만들어진 메소들을 간편하게 호출하는 방법 제공 API
        // System.out.println("메소드호출 정석"); , System.out::println("메소드호출 레퍼런스방식");

    }
}
