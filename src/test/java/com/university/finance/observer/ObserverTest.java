package com.university.finance.observer;

import com.university.finance.model.Transaction;
import com.university.finance.pattern.observer.AuditLogger;
import com.university.finance.pattern.observer.NotificationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ObserverTest {

    @Test
    void auditLoggerShouldHandleUpdate() {
        AuditLogger logger = new AuditLogger();
        assertDoesNotThrow(() -> logger.update(new Transaction()));
    }

    @Test
    void notificationServiceShouldHandleUpdate() {
        NotificationService service = new NotificationService();
        assertDoesNotThrow(() -> service.update(new Transaction()));
    }

    @Test
    void observersShouldNotCrashOnNullTransaction() {
        AuditLogger logger = new AuditLogger();
        assertDoesNotThrow(() -> logger.update(null));
    }

    @Test
    void notificationShouldAcceptNullTransaction() {
        NotificationService service = new NotificationService();
        assertDoesNotThrow(() -> service.update(null));
    }
}
