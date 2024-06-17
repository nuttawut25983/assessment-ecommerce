package maze.lucablock.assessmentecommerce.product.reponse;

import java.math.BigDecimal;
import maze.lucablock.assessmentecommerce.entity.Brand;

public record ProductResponse(int id, String name, String sku, BigDecimal price,
                              String description, String imageUrl, Brand brand) {}
