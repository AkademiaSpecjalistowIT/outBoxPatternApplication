package pl.akademiaspecjalistowit.outboxpatternapplication.order.dto;

import java.util.List;

public record OrderDto(AmountDto amount, List<ProductDto> products) {
}
