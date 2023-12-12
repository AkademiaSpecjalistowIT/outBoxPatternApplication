package pl.akademiaspecjalistowit.outboxpatternapplication.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akademiaspecjalistowit.outboxpatternapplication.accounting.PaymentService;
import pl.akademiaspecjalistowit.outboxpatternapplication.accounting.PaymentStatus;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.entity.OrderEntity;

@AllArgsConstructor
@Service
@Slf4j
public class OrderInstallmentService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;


    @Transactional
    public void makeInstallmentForOrder(Long orderId) {
        orderRepository.findById(orderId)
            .ifPresent(orderEntity -> {
                PaymentStatus paymentStatus = paymentService
                    .pay(orderEntity.getTechnicalId(), orderEntity.getAmount().toDto());
                orderEntity.updatePaymentStatus(OrderEntity.PaymentStatus.valueOf(paymentStatus.toString()));
                log.info("Order {} updated with payment status {}", orderId, orderEntity.getPaymentStatus());
            });
    }

}
