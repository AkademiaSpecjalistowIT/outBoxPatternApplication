package pl.akademiaspecjalistowit.outboxpatternapplication.order.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.dto.AmountDto;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AmountEntity {
    private BigDecimal value;
    private String currency;

    public AmountDto toDto() {
        return new AmountDto(currency, value.toString());
    }
}
