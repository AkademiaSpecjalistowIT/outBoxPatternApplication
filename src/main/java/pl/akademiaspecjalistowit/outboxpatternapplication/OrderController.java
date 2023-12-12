package pl.akademiaspecjalistowit.outboxpatternapplication;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.OrderService;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.OrderDto;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.OrderResponse;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Transactional(timeout = 3)
    public OrderResponse placeAnOrder(@RequestBody OrderDto orderDto) {
        log.info("New order {} received", orderDto);
        return orderService.placeAnOrder(orderDto);
    }

    @PostMapping("/multi")
    @Transactional(timeout = 3)
    public List<OrderResponse> placeAnOrders(@RequestBody List<OrderDto> orderDtos) {
        log.info("New orders {} received", orderDtos);
        return orderDtos.stream().map(orderService::placeAnOrder).toList();
    }
    //todo place multiple orders
}
