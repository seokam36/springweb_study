package example.day12;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    // JPA 사용 시 기본적인 CRUD 제공
    // * 메소드쿼리(망명규칙) 또는 네이티브쿼리 추가 정의
    // findByXXX : XXX에 필드명 넣어서 조회 추상메소드 만들기 , 필드명은 카멜표기법!!
    MemberEntity findByMid(String mid); // mid 이용해서 엔티티 조회
}
