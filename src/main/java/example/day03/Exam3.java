package example.day03;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Exam3 {
    public static void main(String[] args) {
    }
}

// [1] 전통 방식 객체 생성
class SampleDao{
    void method(){
        System.out.println("method");
    }
}
class SampleController{
    void method(){
        // 1. 다른 클래스내 메소드 호출 , 인스턴스생성후 메소드 호출
        SampleDao sampleDao = new SampleDao();
        sampleDao.method();
    }
}

// [2] 전통 방식의 싱글톤 생성
class SampleDao2{
    private SampleDao2(){}
    private static final SampleDao2 instance = new SampleDao2();
    public static SampleDao2 getInstance(){return instance;}
    public void method(){}
}
class SampleController2{
    SampleDao2 dao2 = SampleDao2.getInstance();
    void method(){
        // 2. 다른 클래스내 메소드 호출
        dao2.method();
    }
}

// [3] 스프링 IOC/DI
@Component // IOC규칙으로 스프링 컨테이너에 자동 빈 생성
class SampleDao3{
    // 싱글톤 생략
    void method(){};
}
class SampleController3{
    @Autowired // 의존성 주입 , 스프링 컨테이너에 등록된 빈 가져와서 대입
    private SampleDao3 dao3;
    void method(){
        dao3.method();
    }
}

class SampleController4 {
    // 2. 스프링에서 권장하는 방식
    private final SampleDao3 dao3;
    @Autowired
    public SampleController4(SampleDao3 dao3){
        this.dao3 = dao3;
    }
    void method(){}
}

@RequiredArgsConstructor // final 멤버변수 생성자 자동생성
class SampleController5{
    // 3. 롬복 이용한 방식
    private final SampleDao3 dao3;
}

/*
* 스프링이 켜지면서 폴더내 @Component 찾아내서 스프링 컨테이너 등록주조
* AppStart : 최상위 위치
* */
