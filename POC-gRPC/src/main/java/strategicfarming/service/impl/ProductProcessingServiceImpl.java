package strategicfarming.service.impl;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;
import strategicfarming.dto.ProcessingResult;
import strategicfarming.dto.ProductCommand;
import strategicfarming.service.ProductProcessingService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductProcessingServiceImpl implements ProductProcessingService {

    @Timed(
            value = "product.processing.time",
            description = "Time taken to process product batch"
    )
    @Override
    public ProcessingResult process(List<ProductCommand> products) {

        long start = System.currentTimeMillis();

        int processed = 0;
        int failed = 0;

        for (ProductCommand product : products) {
            try {
                applyBusinessLogic(product);
                processed++;
            } catch (Exception e) {
                failed++;
            }
        }

        long duration = System.currentTimeMillis() - start;

        return new ProcessingResult(processed, failed, duration);
    }


    private void applyBusinessLogic(ProductCommand product) {

        // 1️⃣ Simulate complex pricing calculation
        BigDecimal basePrice = product.price();

        BigDecimal tax = basePrice.multiply(BigDecimal.valueOf(0.18));
        BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.05));

        BigDecimal finalPrice = basePrice
                .add(tax)
                .subtract(discount);

        // 2️⃣ Simulate heavy CPU computation
        heavyComputation(product.materialCode());

        // 3️⃣ Simulate material routing logic
        switch (product.materialType()) {
            case "FINISHED_GOOD" -> finalPrice = finalPrice.multiply(BigDecimal.valueOf(1.10));
            case "RAW" -> finalPrice = finalPrice.multiply(BigDecimal.valueOf(0.95));
            case "SEMI_FINISHED" -> finalPrice = finalPrice.multiply(BigDecimal.valueOf(1.05));
            case "PACKAGING" -> finalPrice = finalPrice.multiply(BigDecimal.valueOf(0.90));
            default -> finalPrice = BigDecimal.ZERO;
        }
    }

    private void heavyComputation(String input) {

        long result = 0;

        // Increase this number to increase CPU load
        for (int i = 0; i < 10; i++) {
            result += Math.sqrt(i * 123.456);
            result = result ^ input.hashCode();
        }
    }


}

