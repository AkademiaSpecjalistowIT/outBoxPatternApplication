package pl.akademiaspecjalistowit.outboxpatternapplication.accounting;

import java.util.UUID;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.AmountDto;

@Service
public class PaymentService {

    @SneakyThrows
    public PaymentStatus pay(UUID oderTechnicalId, AmountDto amountDto) {
        Thread.sleep(3500);
        return PaymentStatus.COMPLETED;
    }
}
