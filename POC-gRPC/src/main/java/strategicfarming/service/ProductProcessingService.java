package strategicfarming.service;

import strategicfarming.dto.ProcessingResult;
import strategicfarming.dto.ProductCommand;

import java.util.List;

public interface ProductProcessingService {

    ProcessingResult process(List<ProductCommand> products);
}

