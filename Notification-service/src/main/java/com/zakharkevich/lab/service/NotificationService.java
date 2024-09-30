package com.zakharkevich.lab.service;

import com.zakharkevich.lab.model.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @JmsListener(destination = "bookingNotifications")
    public void receiveNotification(NotificationDto notification) {
        log.info("Received notification for booking {}: status is {}",
                notification.getBookingId(), notification.getStatus());

        if ("APPROVED".equals(notification.getStatus().toString())) {
            log.info("Notifying user: Booking {} has been approved", notification.getBookingId());
        } else if ("REJECTED".equals(notification.getStatus().toString())) {
            log.info("Notifying user: Booking {} has been rejected", notification.getBookingId());
        }
    }
}