package pl.akademiaspecjalistowit.outboxpatternapplication.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.outboxpatternapplication.order.entity.OrderEntity;

@Repository
interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
