package pl.akademiaspecjalistowit.outboxpatternapplication.order.dto;

import java.util.UUID;

public record ProductDto(UUID productId, Integer amount) {
}
