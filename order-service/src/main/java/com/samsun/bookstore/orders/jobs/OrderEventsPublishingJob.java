package com.samsun.bookstore.orders.jobs;

import com.samsun.bookstore.orders.domain.OrderEventService;
import java.time.Instant;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class OrderEventsPublishingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderEventsPublishingJob.class);

    private final OrderEventService orderEventService;

    OrderEventsPublishingJob(OrderEventService orderEventService) {
        this.orderEventService = orderEventService;
    }

    @Scheduled(cron = "${orders.publish-order-events-job-cron}")
    @SchedulerLock(name = "publishOrderEvents")
    public void publishOrderEvents() {
        // To assert that the lock is held (prevents misconfiguration errors)
        LockAssert.assertLocked();
        // do something
        log.info("Publishing Order Events at {}", Instant.now());
        orderEventService.publishOrderEvents();
    }
}
