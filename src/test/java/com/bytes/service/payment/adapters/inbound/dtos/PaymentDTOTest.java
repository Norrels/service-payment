package com.bytes.service.payment.adapters.inbound.dtos;

import com.bytes.service.payment.domain.models.PaymentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDTOTest {

    @Test
    void shouldCreatePaymentDTOWithCorrectProperties() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder_id(100L);
        paymentDTO.setPaymentType(PaymentType.QRCODE);
        paymentDTO.setTotal(new BigDecimal("99.99"));
        paymentDTO.setExternal_id(123L);

        assertEquals(100L, paymentDTO.getOrder_id());
        assertEquals(PaymentType.QRCODE, paymentDTO.getPaymentType());
        assertEquals(new BigDecimal("99.99"), paymentDTO.getTotal());
        assertEquals(123L, paymentDTO.getExternal_id());
    }

    @Test
    void shouldHandleNullValues() {
        PaymentDTO paymentDTO = new PaymentDTO();

        assertNull(paymentDTO.getOrder_id());
        assertNull(paymentDTO.getPaymentType());
        assertNull(paymentDTO.getTotal());
        assertNull(paymentDTO.getExternal_id());
    }

    @Test
    void shouldSetAndGetOrderId() {
        PaymentDTO paymentDTO = new PaymentDTO();
        Long orderId = 500L;

        paymentDTO.setOrder_id(orderId);

        assertEquals(orderId, paymentDTO.getOrder_id());
    }

    @Test
    void shouldSetAndGetPaymentType() {
        PaymentDTO paymentDTO = new PaymentDTO();
        PaymentType paymentType = PaymentType.QRCODE;

        paymentDTO.setPaymentType(paymentType);

        assertEquals(paymentType, paymentDTO.getPaymentType());
    }

    @Test
    void shouldSetAndGetTotal() {
        PaymentDTO paymentDTO = new PaymentDTO();
        BigDecimal total = new BigDecimal("150.75");

        paymentDTO.setTotal(total);

        assertEquals(total, paymentDTO.getTotal());
    }

    @Test
    void shouldSetAndGetExternalId() {
        PaymentDTO paymentDTO = new PaymentDTO();
        Long externalId = 789L;

        paymentDTO.setExternal_id(externalId);

        assertEquals(externalId, paymentDTO.getExternal_id());
    }
}