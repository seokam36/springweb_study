package example.day04;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ExamController {
    // 서비스객체 (주입)불러오기
    private final ExamService service;
    private final ExamRepository examRepository;

    // [1] 전체 조회
    @GetMapping ("/day04/exam")
    public List<ExamEntity> findAll(){
        // 서비스 호출
        return service.findAll();
    }

    // [2] 저장
    @PostMapping("/day04/exam")
    public boolean save(@RequestBody ExamEntity entity){
        // 서비스 호출
        return service.save(entity);
    }

    // [3] 삭제
    @DeleteMapping("/day04/exam")
    public boolean delete(@RequestParam int no){
        // 서비스 호출
        return service.delete(no);
    }

    // [4] 수정
    @PutMapping("/day04/exam")
    public boolean update(@RequestBody ExamEntity entity){
        return service.update(entity);
    }
}

// @Service : 비즈니스로직, 트랜잭션 담당하는 객체 등록
// @Repository : Entity 영속성 연결하는 인터페이스 등록
// @Entity : 데이터베이스에 맵핑된 테이블의 레코드 모델 객체 등록
// @Table : 맵핑할 테이블명 지정 , 생략시 클래스명으로 지정
// @Id : PK 지정
// @Transactional : 여러 SQL문을 하나의 단위로 묶음
