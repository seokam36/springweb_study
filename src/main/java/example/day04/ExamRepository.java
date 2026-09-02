package example.day04;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 리포지토리(DB조작) 담당하는 객체(빈) 등록
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
    // extends JpaRepository < 조작할엔티티명, 엔티티 PK 타입>

}
