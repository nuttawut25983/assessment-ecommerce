package maze.lucablock.assessmentecommerce.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maze.lucablock.assessmentecommerce.product.reponse.ProductResponse;

@Builder
@Entity
@Table(
    name = "product",
    uniqueConstraints = {@UniqueConstraint(columnNames = "sku")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Size(max = 255)
  private String name;

  @NotBlank
  @Size(max = 255)
  private String sku;

  @NotNull
  private BigDecimal price;

  private String description;

  private String imageUrl;

  @Column(name = "created_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate = new Date();

  @Column(name = "modified_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modifiedDate = new Date();

  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;


  public ProductResponse toResponse() {
    return new ProductResponse(this.id, this.name, this.sku, this.price, this.description, this.imageUrl, this.brand);
  }

}

