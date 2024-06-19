package maze.lucablock.assessmentecommerce.product.service;

import maze.lucablock.assessmentecommerce.entity.Product;
import maze.lucablock.assessmentecommerce.product.reponse.ProductList;
import maze.lucablock.assessmentecommerce.product.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Product createProduct(ProductRequest productRequest);

  Page<ProductList> getAllProducts(Pageable pageable,Boolean ascending);

  Page<Product> getAllProductsAdmin(Pageable pageable,String sortField,Boolean ascending);


  Product getProductById(Integer id);

  Product updateProduct(Integer id, ProductRequest productRequest);

  Boolean deleteProduct(Integer id);
}
