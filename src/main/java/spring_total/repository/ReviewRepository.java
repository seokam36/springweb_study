package spring_total.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_total.model.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
}
