package maze.lucablock.assessmentecommerce.brand.service;

import java.util.List;
import maze.lucablock.assessmentecommerce.brand.request.RequestBrand;
import maze.lucablock.assessmentecommerce.entity.Brand;

public interface BrandService {

  Brand createBrand(RequestBrand requestBrand);

  Brand updateBrand(Long id,RequestBrand requestBrand);

  void deleteBrand(Long id);

  List<Brand> getBrands();

}
