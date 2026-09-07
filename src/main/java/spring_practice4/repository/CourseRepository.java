package spring_practice4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_practice4.Entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
}
