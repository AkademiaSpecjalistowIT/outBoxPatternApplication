package pl.akademiaspecjalistowit.outboxpatternapplication.accounting;

import java.util.UUID;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.AmountDto;

@Service
public class PaymentService {

    @SneakyThrows
    public PaymentStatus pay(UUID oderTechnicalId, AmountDto amountDto) {
        Thread.sleep(1500);
        return PaymentStatus.COMPLETED;
    }
}
