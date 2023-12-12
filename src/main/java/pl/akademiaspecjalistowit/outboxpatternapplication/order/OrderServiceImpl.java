package pl.akademiaspecjalistowit.outboxpatternapplication.order;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
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

    private final OrderRepository orderRepository;
    private final OrderInstallmentService orderInstallmentService;
    private final JobScheduler jobScheduler;


    @Transactional
    public OrderResponse placeAnOrder(OrderDto orderDto) {
        validateOrder(orderDto);

        OrderEntity orderEntity = registerAnOrder(orderDto);
        orderInstallmentService.makeInstallmentForOrder(orderEntity.getId());
        log.info("new order {} saved successfully", orderEntity);
        return new OrderResponse(orderEntity.getTechnicalId(),
            orderEntity.getPaymentStatus(),
            orderEntity.getCreationDate());
    }


    private void validateOrder(OrderDto orderDto) {
        //TODO implementation
    }

    private OrderEntity registerAnOrder(OrderDto orderDto) {
        return orderRepository.save(new OrderEntity(
            UUID.randomUUID(),
            new AmountEntity(new BigDecimal(
                orderDto.amount().value()),
                orderDto.amount().currency()))
        );
    }
}
