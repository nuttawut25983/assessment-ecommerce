package maze.lucablock.assessmentecommerce.product.reponse;

import java.math.BigDecimal;
import java.util.Date;

public record ProductList(
    int id, String name, BigDecimal price, String imageUrl, String brandName, Date createDate, Date modifiedDate
) {

}
