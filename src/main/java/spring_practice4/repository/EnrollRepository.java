package spring_practice4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_practice4.Entity.EnrollEntity;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Integer> {
}
