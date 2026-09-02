package example.day04;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 비즈니스로직 담당하는 빈 등록
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository repository;

    // [1] 전체조회
    public List<ExamEntity> findAll() {
        // 리포지토리 호출
        // 리포지토리.findAll() : (구현체) select 지원
        // 매핑된 테이블의 모든 레코드 반환
        return repository.findAll();
    }

    // [2] 저장
    public boolean save(ExamEntity entity) {
       // 리포지토리 호출
       // 리포지토리.save(저장할entity) : insert 지원
        // save 반환값은 영속된 엔티티 반환
       ExamEntity saved = repository.save(entity);
       if (saved.getEno() >= 1){
           return true;
       }
       return false;
    }


    public boolean delete(int no) {
        // 리포지토리 객체 .deleteById( 삭제할 PK번호 )
        // 리포지토리 객체 .delete( 삭제할 엔티티 )ㄷ
        // 반환타입 : 없음, 삭제여부 findXXX 이용하여 확인
        repository.deleteById(no);
        return true;
    }

    @Transactional // 트랜잭션 -> 여러개 sql을 하나의 단위로 묶음
    public boolean update(ExamEntity entity) {
        // 1. 영속된 엔티티 조회 (PK)
        // 리포지토리객체.findById( 조회할 pk번호 ) : select SQL 지원
        // 반환타입 : Optional<엔티티>
        // Optional클래스 : 본문(객체) 감싼 클래스
        // -> 만약 조회 결과 엔티티가 없을 때 .getEno() 오류 발생
        // 객체 랩핑해서 null 검사 지원
        Optional<ExamEntity> optional = repository.findById(entity.getEno());
        // 2. 조회된 결과 엔티티 여부 확인
        if (optional.isPresent()){ // 객체가 있으면 true , 없으면 false
            ExamEntity savedEntity = optional.get();// 랩핑된 optional에서 엔티티 꺼내기
            savedEntity.setEname(entity.getEname());
            return true;
        }
        return false;
    }
}
