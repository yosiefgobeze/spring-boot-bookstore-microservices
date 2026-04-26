package com.samsun.bookstore.notifications.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {
    // check if eventId exists or not, prevents duplicate entries
    boolean existsByEventId(String eventId);
}
