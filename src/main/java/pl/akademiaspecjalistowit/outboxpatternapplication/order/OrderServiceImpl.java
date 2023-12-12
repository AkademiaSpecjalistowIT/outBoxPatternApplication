package pl.akademiaspecjalistowit.outboxpatternapplication.order;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akademiaspecjalistowit.outboxpatternapplication.accounting.PaymentService;
import pl.akademiaspecjalistowit.outboxpatternapplication.accounting.PaymentStatus;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.OrderDto;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.OrderResponse;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.entity.AmountEntity;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.entity.OrderEntity;

@Slf4j
@Service
@AllArgsConstructor
class OrderServiceImpl implements OrderService {

    private final PaymentService paymentService;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponse placeAnOrder(OrderDto orderDto) {
        validateOrder(orderDto);

        UUID orderTechnicalId = UUID.randomUUID();

        PaymentStatus paymentStatus = paymentService
            .pay(orderTechnicalId, orderDto.amount());

        OrderEntity orderEntity = registerAnOrder(orderDto, paymentStatus, orderTechnicalId);
        log.info("new order {} saved successfully", orderEntity);
        return new OrderResponse(orderEntity.getTechnicalId(), orderEntity.getCreationDate());
    }

    private void validateOrder(OrderDto orderDto) {
        //TODO implementation
    }

    private OrderEntity registerAnOrder(OrderDto orderDto,
                                        PaymentStatus paymentStatus, UUID orderTechnicalId) {
        return orderRepository.save(new OrderEntity(
            orderTechnicalId,
            new AmountEntity(new BigDecimal(
                orderDto.amount().value()),
                orderDto.amount().currency()),
            OrderEntity.PaymentStatus.valueOf(paymentStatus.toString())
        ));
    }
}
