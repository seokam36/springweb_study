package example.day07;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    // 쿼리메소드 : SQL 작성하지 않고 추상메소드 이름으로 쿼리 자동 생성 < 카멜표기법 >
    // 반환타입 findBy필드명( 타입 매개변수명 ); 주의할점 : 필드명에 카멜표기법 , 존재하는 필드명
    TodoEntity findByTitle(String title);
    List<TodoEntity> findByTitleAndContent(String tile, String content);
    Map<String, Object> findByTitleOrContent(String title, String content);

    // 네이티브쿼리 : SQL 직접 작성, 추상메소드 이름은 아무거나 가능
    // SQL 문법내 매개변수 대입시 ? 대신에 :매개변수명
    @Query(value = "select * from todo where title = :title", nativeQuery = true)
    TodoEntity myQuery1(String title);

    @Query(value = "select * from todo where title = :title and content = :content", nativeQuery = true)
    List<TodoEntity> myQuery2(String title, String content);

    @Query(value = "select * from todo where title = :title and content = :content", nativeQuery = true)
    List<Map<String, Object>> myQuery3(String title, String content);
}
