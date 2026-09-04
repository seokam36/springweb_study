package spring_activity1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_activity1.Entity.ProductLogEntity;

public interface ProductLogRepository extends JpaRepository<ProductLogEntity, Integer> {
}
