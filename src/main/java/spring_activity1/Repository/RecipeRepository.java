package spring_activity1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_activity1.Entity.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
}
