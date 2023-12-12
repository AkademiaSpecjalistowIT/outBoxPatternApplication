package pl.akademiaspecjalistowit.outboxpatternapplication.order.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(UUID orderTechnicalId, LocalDateTime createDateTime) {
}
