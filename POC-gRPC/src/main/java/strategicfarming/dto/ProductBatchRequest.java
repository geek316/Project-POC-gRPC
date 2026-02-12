package strategicfarming.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record ProductBatchRequest(
        @NotEmpty
        List<ProductCommand> products
) {}
