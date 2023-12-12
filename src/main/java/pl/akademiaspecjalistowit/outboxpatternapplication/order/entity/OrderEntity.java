package pl.akademiaspecjalistowit.outboxpatternapplication.order.entity;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class OrderEntity {

    public OrderEntity(UUID technicalId,
                       AmountEntity amount) {
        this.technicalId = technicalId;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.PENDING;
        this.creationDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID technicalId;

    @Embedded
    @AttributeOverride(name="currency", column = @Column(name = "amount_currency"))
    @AttributeOverride(name="value", column = @Column(name = "amount_value"))
    private AmountEntity amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    private LocalDateTime updateDate;


    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED
    }

    public void updatePaymentStatus(PaymentStatus paymentStatus) {
        if (this.paymentStatus.equals(paymentStatus)) {
            throw new OrderException(String.format("Order is already in %s status", this.paymentStatus));
        }
        if (!this.paymentStatus.equals(PaymentStatus.PENDING)) {
            throw new OrderException(String.format("Order is already in its final state %s", this.paymentStatus));
        }
        this.paymentStatus = paymentStatus;
        this.updateDate = LocalDateTime.now();
    }
}
