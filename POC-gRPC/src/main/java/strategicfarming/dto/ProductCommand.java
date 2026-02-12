package strategicfarming.dto;

import java.math.BigDecimal;

public record ProductCommand(
        String materialCode,
        String description,
        String plant,
        String materialType,
        BigDecimal price,
        String currency
) {
}
