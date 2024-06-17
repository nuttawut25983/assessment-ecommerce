package maze.lucablock.assessmentecommerce.product.repository;


import maze.lucablock.assessmentecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


}
