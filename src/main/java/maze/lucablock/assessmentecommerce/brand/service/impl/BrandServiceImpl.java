package maze.lucablock.assessmentecommerce.brand.service.impl;


import java.util.List;
import maze.lucablock.assessmentecommerce.brand.repository.BrandRepository;
import maze.lucablock.assessmentecommerce.brand.request.RequestBrand;
import maze.lucablock.assessmentecommerce.brand.service.BrandListDto;
import maze.lucablock.assessmentecommerce.brand.service.BrandService;
import maze.lucablock.assessmentecommerce.entity.Brand;
import maze.lucablock.assessmentecommerce.exceptions.BadRequestException;
import maze.lucablock.assessmentecommerce.exceptions.InternalServerException;
import maze.lucablock.assessmentecommerce.exceptions.NotFoundException;
import maze.lucablock.assessmentecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;
  private final ProductRepository productRepository;

  public BrandServiceImpl(BrandRepository brandRepository, ProductRepository productRepository) {
    this.brandRepository = brandRepository;
    this.productRepository = productRepository;
  }

  @Override
  public Brand createBrand(RequestBrand requestBrand) {
    brandRepository.findByName(requestBrand.getName()).ifPresent(brand -> {
      throw new BadRequestException("Brand already exists");
    });
    Brand brand = Brand.builder().name(requestBrand.getName()).build();
    return brandRepository.save(brand);
  }

  @Override
  public Brand updateBrand(Long id, RequestBrand requestBrand) {
    brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand not found"));
    brandRepository.findByName(requestBrand.getName()).ifPresent(brand -> {
      throw new BadRequestException("Brand already exists");
    });
    Brand brand = Brand.builder().id(id).name(requestBrand.getName()).build();
    return brandRepository.save(brand);
  }

  @Override
  public void deleteBrand(Long id) {
    try {
      brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand not found"));
      brandRepository.deleteById(id);
    } catch (Exception e) {
      throw new InternalServerException("Internal server error");
    }
  }

  @Override
  public List<Brand> getBrands() {
    return brandRepository.findAll();
  }

  @Override
  public List<BrandListDto> getBrandsAdmin() {
     List<Brand> brands = brandRepository.findAll();
      return brands.stream().map(brand -> new BrandListDto(brand.getId(), brand.getName(),
          productRepository.findProductCountByBrandId(brand.getId()))).toList();
  }
}
