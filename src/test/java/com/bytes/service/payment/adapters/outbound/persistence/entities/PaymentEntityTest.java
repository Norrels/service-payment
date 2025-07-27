package com.bytes.service.payment.adapters.outbound.persistence.entities;

import com.bytes.service.payment.domain.models.PaymentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityTest {

    @Test
    void shouldCreatePaymentEntityWithCorrectProperties() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(1L);
        paymentEntity.setOrderId(100L);
        paymentEntity.setPaymentType(PaymentType.QRCODE);
        paymentEntity.setTotal(new BigDecimal("99.99"));
        LocalDateTime now = LocalDateTime.now();
        paymentEntity.setCreatedAt(now);
        paymentEntity.setExternalId(123L);

        assertEquals(1L, paymentEntity.getId());
        assertEquals(100L, paymentEntity.getOrderId());
        assertEquals(PaymentType.QRCODE, paymentEntity.getPaymentType());
        assertEquals(new BigDecimal("99.99"), paymentEntity.getTotal());
        assertEquals(now, paymentEntity.getCreatedAt());
        assertEquals(123L, paymentEntity.getExternalId());
    }

    @Test
    void shouldHandleNullValues() {
        PaymentEntity paymentEntity = new PaymentEntity();

        assertNull(paymentEntity.getId());
        assertNull(paymentEntity.getOrderId());
        assertNull(paymentEntity.getPaymentType());
        assertNull(paymentEntity.getTotal());
        assertNull(paymentEntity.getCreatedAt());
        assertNull(paymentEntity.getExternalId());
    }

    @Test
    void shouldSetAndGetId() {
        PaymentEntity paymentEntity = new PaymentEntity();
        Long id = 5L;

        paymentEntity.setId(id);

        assertEquals(id, paymentEntity.getId());
    }

    @Test
    void shouldSetAndGetOrderId() {
        PaymentEntity paymentEntity = new PaymentEntity();
        Long orderId = 200L;

        paymentEntity.setOrderId(orderId);

        assertEquals(orderId, paymentEntity.getOrderId());
    }

    @Test
    void shouldSetAndGetPaymentType() {
        PaymentEntity paymentEntity = new PaymentEntity();
        PaymentType paymentType = PaymentType.QRCODE;

        paymentEntity.setPaymentType(paymentType);

        assertEquals(paymentType, paymentEntity.getPaymentType());
    }

    @Test
    void shouldSetAndGetTotal() {
        PaymentEntity paymentEntity = new PaymentEntity();
        BigDecimal total = new BigDecimal("150.75");

        paymentEntity.setTotal(total);

        assertEquals(total, paymentEntity.getTotal());
    }

    @Test
    void shouldSetAndGetCreatedAt() {
        PaymentEntity paymentEntity = new PaymentEntity();
        LocalDateTime createdAt = LocalDateTime.now();

        paymentEntity.setCreatedAt(createdAt);

        assertEquals(createdAt, paymentEntity.getCreatedAt());
    }

    @Test
    void shouldSetAndGetExternalId() {
        PaymentEntity paymentEntity = new PaymentEntity();
        Long externalId = 456L;

        paymentEntity.setExternalId(externalId);

        assertEquals(externalId, paymentEntity.getExternalId());
    }
}