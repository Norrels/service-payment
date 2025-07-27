package com.bytes.service.payment.application;

import com.bytes.service.payment.application.useCases.CreatePaymentUseCase;
import com.bytes.service.payment.application.useCases.FindPaymentByOrderIdUseCase;
import com.bytes.service.payment.application.useCases.PayOrderUseCase;
import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase;

    @Mock
    private PayOrderUseCase payOrderUseCase;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(createPaymentUseCase, findPaymentByOrderIdUseCase, payOrderUseCase);
    }

    @Test
    void shouldCreatePaymentAndExecutePayOrder() {
        Payment inputPayment = new Payment(null, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        Payment createdPayment = new Payment(1L, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        when(createPaymentUseCase.execute(inputPayment)).thenReturn(createdPayment);

        Payment result = paymentService.create(inputPayment);

        assertEquals(createdPayment, result);
        verify(createPaymentUseCase).execute(inputPayment);
        verify(payOrderUseCase).execute(100L);
    }

    @Test
    void shouldFindPaymentByOrderId() {
        Long orderId = 100L;
        Payment expectedPayment = new Payment(1L, orderId, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        when(findPaymentByOrderIdUseCase.execute(orderId)).thenReturn(expectedPayment);

        Payment result = paymentService.findByOrderId(orderId);

        assertEquals(expectedPayment, result);
        verify(findPaymentByOrderIdUseCase).execute(orderId);
    }
}