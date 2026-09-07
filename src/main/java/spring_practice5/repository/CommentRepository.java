package spring_practice5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_practice5.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
