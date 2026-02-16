package strategicfarming.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import strategicfarming.dto.ProcessingResult;
import strategicfarming.dto.ProductCommand;
import strategicfarming.service.ProductProcessingService;

import java.math.BigDecimal;
import java.util.List;

@GrpcService
public class ProductGrpcService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductProcessingService processingService;

    public ProductGrpcService(ProductProcessingService processingService) {
        this.processingService = processingService;
    }

    @Override
    public void processProducts(ProductBatchRequest request,
                                StreamObserver<ProductBatchResponse> responseObserver) {

        List<ProductCommand> commands = request.getProductsList()
                .stream()
                .map(p -> new ProductCommand(
                        p.getMaterialCode(),
                        p.getDescription(),
                        p.getPlant(),
                        p.getMaterialType(),
                        BigDecimal.valueOf(p.getPrice()),
                        p.getCurrency()
                ))
                .toList();

        ProcessingResult result = processingService.process(commands);

        ProductBatchResponse response = ProductBatchResponse.newBuilder()
                .setProcessed(result.processed())
                .setFailed(result.failed())
                .setDurationMs(result.durationMs())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
