package maze.lucablock.assessmentecommerce.product.service.impl;


import java.util.Date;
import maze.lucablock.assessmentecommerce.brand.repository.BrandRepository;
import maze.lucablock.assessmentecommerce.entity.Brand;
import maze.lucablock.assessmentecommerce.entity.Product;
import maze.lucablock.assessmentecommerce.exceptions.BadRequestException;
import maze.lucablock.assessmentecommerce.exceptions.NotFoundException;
import maze.lucablock.assessmentecommerce.product.reponse.ProductList;
import maze.lucablock.assessmentecommerce.product.repository.ProductRepository;
import maze.lucablock.assessmentecommerce.product.request.ProductRequest;
import maze.lucablock.assessmentecommerce.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final BrandRepository brandRepository;

  public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository) {
    this.productRepository = productRepository;
    this.brandRepository = brandRepository;
  }

  @Override
  public Product createProduct(ProductRequest productRequest) {
    Brand brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow(() -> new NotFoundException("Brand not found"));
    productRepository.findDistinctBySku(productRequest.getProductName()).ifPresent(product -> {
      throw new BadRequestException("Product already exists");
    });

    Product product= Product.builder()
        .name(productRequest.getProductName())
        .price(productRequest.getPrice())
        .brand(brand)
        .sku(productRequest.getProductSku())
        .imageUrl(productRequest.getImageUrl())
        .description(productRequest.getDescription())
        .createDate(Date.from(java.time.Instant.now()))
        .modifiedDate(Date.from(java.time.Instant.now()))
        .build();
    return productRepository.save(product);
  }

  @Override
  public Page<ProductList> getAllProducts(Pageable pageable, Boolean ascending) {
    return productRepository.findAll(pageable).map(product -> new ProductList(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getImageUrl(),
        product.getBrand().getName(),
        product.getCreateDate(),
        product.getModifiedDate()
    ));
  }

  @Override
  public Page<Product> getAllProductsAdmin(Pageable pageable, String sortField, Boolean ascending) {
    Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortField);
    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    return productRepository.findAll(pageable);
  }

  @Override
  public Product getProductById(Integer id) {
    return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
  }

  @Override
  public Product updateProduct(Integer id, ProductRequest productRequest) {
    try {
      Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
      Brand brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow(() -> new NotFoundException("Brand not found"));

      product.setName(productRequest.getProductName());
      product.setPrice(productRequest.getPrice());
      product.setBrand(brand);
      product.setSku(productRequest.getProductSku());
      product.setDescription(productRequest.getDescription());
      return productRepository.save(product);

    } catch (Exception e) {
      throw new BadRequestException("Bad request");
    }
  }

  @Override
  public Boolean deleteProduct(Integer id) {
      try {
        productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.deleteById(id);
        return true;
      } catch (Exception e) {
        throw new NotFoundException("Product not found");
      }
  }
}
