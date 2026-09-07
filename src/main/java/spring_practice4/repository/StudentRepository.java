package spring_practice4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_practice4.Entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}
