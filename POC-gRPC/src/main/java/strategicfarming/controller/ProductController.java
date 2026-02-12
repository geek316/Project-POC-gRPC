package strategicfarming.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategicfarming.dto.ProductBatchRequest;
import strategicfarming.dto.ProductBatchResponse;
import strategicfarming.dto.ProcessingResult;
import strategicfarming.service.ProductProcessingService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductProcessingService productProcessingService;

    @PostMapping("/process")
    public ResponseEntity<ProductBatchResponse> processProducts(
            @Valid @RequestBody ProductBatchRequest request) {

        ProcessingResult result =
                productProcessingService.process(request.products());

        ProductBatchResponse response =
                new ProductBatchResponse(
                        result.processed(),
                        result.failed(),
                        result.durationMs()
                );

        return ResponseEntity.ok(response);
    }
}
