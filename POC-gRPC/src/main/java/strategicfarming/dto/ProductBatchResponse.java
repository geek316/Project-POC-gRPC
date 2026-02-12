package strategicfarming.dto;

public record ProductBatchResponse(
        int processed,
        int failed,
        long durationMs
) {}
