package com.bytes.service.payment.mapper;

import com.bytes.service.payment.adapters.outbound.persistence.entities.PaymentEntity;
import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperTest {

    private PaymentMapper paymentMapper;

    @BeforeEach
    void setUp() {
        paymentMapper = new PaymentMapper();
    }

    @Test
    void shouldMapPaymentEntityToPayment() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(1L);
        paymentEntity.setOrderId(100L);
        paymentEntity.setPaymentType(PaymentType.QRCODE);
        paymentEntity.setTotal(new BigDecimal("99.99"));
        paymentEntity.setCreatedAt(LocalDateTime.now());
        paymentEntity.setExternalId(123L);

        Payment payment = paymentMapper.toPayment(paymentEntity);

        assertNotNull(payment);
        assertEquals(paymentEntity.getId(), payment.getId());
        assertEquals(paymentEntity.getOrderId(), payment.getOrderId());
        assertEquals(paymentEntity.getPaymentType(), payment.getPaymentType());
        assertEquals(paymentEntity.getTotal(), payment.getTotal());
        assertEquals(paymentEntity.getExternalId(), payment.getExternalId());
        assertNotNull(payment.getCreatedAt());
    }

    @Test
    void shouldMapPaymentToPaymentEntity() {
        Payment payment = new Payment(1L, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(payment);

        assertNotNull(paymentEntity);
        assertEquals(payment.getId(), paymentEntity.getId());
        assertEquals(payment.getOrderId(), paymentEntity.getOrderId());
        assertEquals(payment.getPaymentType(), paymentEntity.getPaymentType());
        assertEquals(payment.getTotal(), paymentEntity.getTotal());
        assertEquals(payment.getCreatedAt(), paymentEntity.getCreatedAt());
        assertEquals(payment.getExternalId(), paymentEntity.getExternalId());
    }

    @Test
    void shouldReturnNullWhenPaymentEntityIsNull() {
        Payment payment = paymentMapper.toPayment(null);

        assertNull(payment);
    }

    @Test
    void shouldReturnNullWhenPaymentIsNull() {
        PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(null);

        assertNull(paymentEntity);
    }

    @Test
    void shouldMapPaymentEntityWithNullValues() {
        PaymentEntity paymentEntity = new PaymentEntity();

        Payment payment = paymentMapper.toPayment(paymentEntity);

        assertNotNull(payment);
        assertNull(payment.getId());
        assertNull(payment.getOrderId());
        assertNull(payment.getPaymentType());
        assertNull(payment.getTotal());
        assertNull(payment.getExternalId());
        assertNotNull(payment.getCreatedAt());
    }

    @Test
    void shouldMapPaymentWithNullValues() {
        Payment payment = new Payment(null, null, null, null, null);

        PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(payment);

        assertNotNull(paymentEntity);
        assertNull(paymentEntity.getId());
        assertNull(paymentEntity.getOrderId());
        assertNull(paymentEntity.getPaymentType());
        assertNull(paymentEntity.getTotal());
        assertEquals(payment.getCreatedAt(), paymentEntity.getCreatedAt());
        assertNull(paymentEntity.getExternalId());
    }
}