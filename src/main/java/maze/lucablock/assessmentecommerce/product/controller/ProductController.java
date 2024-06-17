package maze.lucablock.assessmentecommerce.product.controller;

import maze.lucablock.assessmentecommerce.authentication.response.AuthenticationResponse;
import maze.lucablock.assessmentecommerce.entity.Product;
import maze.lucablock.assessmentecommerce.product.request.ProductRequest;
import maze.lucablock.assessmentecommerce.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/product")
public class ProductController {

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public Page<Product> getProducts(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "20") Integer size
  ) {
    return productService.getAllProducts(page, size);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(ProductRequest productRequest) {
    return ResponseEntity.ok(productService.createProduct(productRequest));
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable Integer id) {
    try {
      return productService.getProductById(id);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Product not found");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Integer id, ProductRequest productRequest) {
    return ResponseEntity.ok(productService.updateProduct(id, productRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id) {
    return ResponseEntity.ok(productService.deleteProduct(id));
  }




}
