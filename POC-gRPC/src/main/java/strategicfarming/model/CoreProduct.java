package strategicfarming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "core_product")
public class CoreProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "core_product_seq")
    @SequenceGenerator(
            name = "core_product_seq",
            sequenceName = "core_product_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "material_code", nullable = false, length = 50)
    private String materialCode;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "plant", length = 20)
    private String plant;

    @Column(name = "material_type", length = 30)
    private String materialType;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency", length = 10)
    private String currency;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public CoreProduct(String materialCode,
                       String description,
                       String plant,
                       String materialType,
                       BigDecimal price,
                       String currency) {

        this.materialCode = materialCode;
        this.description = description;
        this.plant = plant;
        this.materialType = materialType;
        this.price = price;
        this.currency = currency;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}


