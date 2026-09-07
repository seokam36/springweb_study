package spring_practice5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_practice5.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
}
