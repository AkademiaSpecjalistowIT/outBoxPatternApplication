package pl.akademiaspecjalistowit.outboxpatternapplication.order.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.entity.OrderEntity;

public record OrderResponse(UUID orderTechnicalId, OrderEntity.PaymentStatus paymentStatus, LocalDateTime createDateTime) {
}
