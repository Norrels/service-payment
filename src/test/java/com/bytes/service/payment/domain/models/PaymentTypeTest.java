package com.bytes.service.payment.domain.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTypeTest {

    @Test
    void shouldHaveQRCodePaymentType() {
        PaymentType paymentType = PaymentType.QRCODE;
        
        assertNotNull(paymentType);
        assertEquals("QRCODE", paymentType.name());
    }

    @Test
    void shouldReturnCorrectValueOf() {
        PaymentType paymentType = PaymentType.valueOf("QRCODE");
        
        assertEquals(PaymentType.QRCODE, paymentType);
    }

    @Test
    void shouldHaveOnlyOneValue() {
        PaymentType[] values = PaymentType.values();
        
        assertEquals(1, values.length);
        assertEquals(PaymentType.QRCODE, values[0]);
    }
}