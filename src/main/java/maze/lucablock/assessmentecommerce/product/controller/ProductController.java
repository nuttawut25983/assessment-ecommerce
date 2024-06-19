package maze.lucablock.assessmentecommerce.product.controller;

import com.sun.security.auth.UserPrincipal;
import io.swagger.v3.oas.annotations.Parameter;
import maze.lucablock.assessmentecommerce.entity.Product;
import maze.lucablock.assessmentecommerce.product.reponse.ProductList;
import maze.lucablock.assessmentecommerce.product.request.ProductRequest;
import maze.lucablock.assessmentecommerce.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public Page<ProductList> getProducts(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "20") Integer size,
      @RequestParam(value = "ascending", defaultValue = "true") Boolean ascending
  ) {
    Pageable pageable = Pageable.ofSize(size).withPage(page);
    return productService.getAllProducts(pageable,ascending);
  }

  @PreAuthorize("hasAnyRole('ADMIN') or ('SUPERADMIN')")
  @GetMapping("/admin")
  public Page<Product> getProductsAdmin(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "20") Integer size,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      @RequestParam(value = "ascending", defaultValue = "true") Boolean ascending
  ) {
    Pageable pageable = Pageable.ofSize(size).withPage(page);
    return productService.getAllProductsAdmin(pageable,sortField,ascending);
  }

  @PreAuthorize("hasAnyRole('ADMIN') or ('SUPERADMIN')")
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
    System.out.println(productRequest);
    return ResponseEntity.ok(productService.createProduct(productRequest));
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable Integer id) {
    return productService.getProductById(id);
  }

  @PreAuthorize("hasAnyRole('ADMIN') or ('SUPERADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Integer id,@RequestBody ProductRequest productRequest) {
    return ResponseEntity.ok(productService.updateProduct(id, productRequest));
  }

  @PreAuthorize("hasAnyRole('ADMIN') or ('SUPERADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id) {
    return ResponseEntity.ok(productService.deleteProduct(id));
  }

}
