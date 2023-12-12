package pl.akademiaspecjalistowit.outboxpatternapplication.order;

import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.OrderDto;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeAnOrder(OrderDto orderDto);
}
