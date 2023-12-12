package pl.akademiaspecjalistowit.outboxpatternapplication.order.entity;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
