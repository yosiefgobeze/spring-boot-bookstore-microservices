package com.samsun.bookstore.orders.jobs;

import com.samsun.bookstore.orders.domain.OrderService;
import java.time.Instant;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class OrderProcessingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderProcessingJob.class);

    private final OrderService orderService;

    OrderProcessingJob(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "${orders.new-orders-job-cron}")
    @SchedulerLock(name = "processNewOrders")
    public void processNewOrders() {
        // To assert that the lock is held (prevents misconfiguration errors)
        LockAssert.assertLocked();
        // do something
        log.info("Processing new orders at {}", Instant.now());
        orderService.processNewOrders();
    }
}
