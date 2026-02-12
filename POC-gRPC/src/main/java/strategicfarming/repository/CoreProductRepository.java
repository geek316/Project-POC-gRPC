package strategicfarming.repository;

import strategicfarming.model.CoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreProductRepository extends JpaRepository<CoreProduct, Long> {
}
