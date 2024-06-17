package maze.lucablock.assessmentecommerce.product.service;

import maze.lucablock.assessmentecommerce.entity.Product;
import maze.lucablock.assessmentecommerce.product.request.ProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {

  Product createProduct(ProductRequest productRequest);

  Page<Product> getAllProducts(Integer page, Integer size);

  Product getProductById(Integer id);

  Product updateProduct(Integer id, ProductRequest productRequest);

  Boolean deleteProduct(Integer id);
}
