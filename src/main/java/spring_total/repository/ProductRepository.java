package spring_total.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_total.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity , Integer> {
}
