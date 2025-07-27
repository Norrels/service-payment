package com.bytes.service.payment.domain.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class PaymentTest {

    @Test
    void shouldCreatePaymentWithCorrectProperties() {
        Long id = 1L;
        Long orderId = 100L;
        PaymentType paymentType = PaymentType.QRCODE;
        BigDecimal total = new BigDecimal("99.99");
        Long externalId = 123L;

        Payment payment = new Payment(id, orderId, paymentType, total, externalId);

        assertEquals(id, payment.getId());
        assertEquals(orderId, payment.getOrderId());
        assertEquals(paymentType, payment.getPaymentType());
        assertEquals(total, payment.getTotal());
        assertEquals(externalId, payment.getExternalId());
        assertNotNull(payment.getCreatedAt());
        assertTrue(payment.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void shouldSetCreatedAtToCurrentTime() {
        LocalDateTime beforeCreation = LocalDateTime.now();
        
        Payment payment = new Payment(1L, 100L, PaymentType.QRCODE, new BigDecimal("50.00"), 456L);
        
        LocalDateTime afterCreation = LocalDateTime.now();
        
        assertNotNull(payment.getCreatedAt());
        assertTrue(payment.getCreatedAt().isAfter(beforeCreation.minusSeconds(1)));
        assertTrue(payment.getCreatedAt().isBefore(afterCreation.plusSeconds(1)));
    }

    @Test
    void shouldHandleNullValues() {
        Payment payment = new Payment(null, null, null, null, null);

        assertNull(payment.getId());
        assertNull(payment.getOrderId());
        assertNull(payment.getPaymentType());
        assertNull(payment.getTotal());
        assertNull(payment.getExternalId());
        assertNotNull(payment.getCreatedAt());
    }
}