package maze.lucablock.assessmentecommerce.brand.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandListDto {

  private Long id;
  private String name;
  private int productCount;
}
