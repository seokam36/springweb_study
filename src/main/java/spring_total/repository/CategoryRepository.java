package spring_total.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_total.model.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
