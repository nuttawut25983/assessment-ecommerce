package maze.lucablock.assessmentecommerce.product.repository;


import java.util.Optional;
import maze.lucablock.assessmentecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {



  Optional<Object> findDistinctBySku(String productName);

  @Query("SELECT COUNT(p) FROM Product p WHERE p.brand.id = :brandId")
  int findProductCountByBrandId(Long brandId);
}
