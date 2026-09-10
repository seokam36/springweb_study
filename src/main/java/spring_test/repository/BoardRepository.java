package spring_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_test.model.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
}
