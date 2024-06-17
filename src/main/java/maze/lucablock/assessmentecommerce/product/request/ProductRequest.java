package maze.lucablock.assessmentecommerce.product.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  @NotNull(message = "productName cannot be null")
  private String productName;
  @NotNull(message = "sku cannot be null")
  private String productSku;
  @NotNull(message = "price cannot be null")
  private BigDecimal price;
  private String description;
  @NotNull(message = "imageUrl cannot be null")
  private String imageUrl;
  @NotNull(message = "brand cannot be null")
  private Long brandId;

}