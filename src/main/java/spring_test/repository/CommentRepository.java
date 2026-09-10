package spring_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_test.model.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
