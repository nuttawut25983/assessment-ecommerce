package maze.lucablock.assessmentecommerce.brand.controller;


import maze.lucablock.assessmentecommerce.brand.request.RequestBrand;
import maze.lucablock.assessmentecommerce.brand.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

  private final BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @PostMapping
  public ResponseEntity<Object> createBrand(@RequestBody RequestBrand requestBrand) {
    return ResponseEntity.ok(brandService.createBrand(requestBrand));
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateBrand(@RequestBody RequestBrand requestBrand,
      @PathVariable Long id) {
    return ResponseEntity.ok(brandService.updateBrand(id,requestBrand));
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteBrand(@PathVariable Long id) {
    brandService.deleteBrand(id);
    return ResponseEntity.ok("Brand deleted");
  }

  @GetMapping
  public ResponseEntity<Object> getBrands() {
    return ResponseEntity.ok(brandService.getBrands());
  }
}
