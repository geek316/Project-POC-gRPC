package strategicfarming.dto;

public record ProcessingResult(
        int processed,
        int failed,
        long durationMs
) {
}

