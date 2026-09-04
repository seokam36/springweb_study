package spring_activity1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_activity1.Entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
