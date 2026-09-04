package spring_activity1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_activity1.Entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
}
