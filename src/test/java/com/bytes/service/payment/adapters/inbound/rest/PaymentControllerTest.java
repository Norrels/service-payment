package com.bytes.service.payment.adapters.inbound.rest;

import com.bytes.service.payment.adapters.inbound.dtos.PaymentDTO;
import com.bytes.service.payment.application.PaymentService;
import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import com.bytes.service.payment.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        paymentController = new PaymentController(paymentService);
    }

    @Test
    void shouldPayOrderSuccessfully() {
        Long orderId = 100L;
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder_id(orderId);
        paymentDTO.setPaymentType(PaymentType.QRCODE);
        paymentDTO.setTotal(new BigDecimal("99.99"));
        paymentDTO.setExternal_id(123L);

        Payment createdPayment = new Payment(1L, orderId, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        when(paymentService.create(any(Payment.class))).thenReturn(createdPayment);

        ResponseEntity<Object> response = paymentController.payOrder(orderId, paymentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdPayment, response.getBody());
        verify(paymentService).create(any(Payment.class));
    }

    @Test
    void shouldThrowBusinessExceptionWhenOrderIdMismatch() {
        Long orderId = 100L;
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder_id(200L);
        paymentDTO.setPaymentType(PaymentType.QRCODE);
        paymentDTO.setTotal(new BigDecimal("99.99"));
        paymentDTO.setExternal_id(123L);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            paymentController.payOrder(orderId, paymentDTO);
        });

        assertEquals("O id do pedido é diferente do id passado como paramêtro", exception.getMessage());
        verify(paymentService, never()).create(any(Payment.class));
    }

    @Test
    void shouldGetPaymentStatusSuccessfully() {
        Long orderId = 100L;
        Payment payment = new Payment(1L, orderId, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        when(paymentService.findByOrderId(orderId)).thenReturn(payment);

        ResponseEntity<Object> response = paymentController.statusOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        
        @SuppressWarnings("unchecked")
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("PAIED", responseBody.get("status"));
        
        verify(paymentService).findByOrderId(orderId);
    }

    @Test
    void shouldCreatePaymentWithCorrectParameters() {
        Long orderId = 100L;
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder_id(orderId);
        paymentDTO.setPaymentType(PaymentType.QRCODE);
        paymentDTO.setTotal(new BigDecimal("99.99"));
        paymentDTO.setExternal_id(123L);

        Payment createdPayment = new Payment(1L, orderId, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        when(paymentService.create(any(Payment.class))).thenReturn(createdPayment);

        paymentController.payOrder(orderId, paymentDTO);

        verify(paymentService).create(argThat(payment -> 
            payment.getOrderId().equals(orderId) &&
            payment.getPaymentType().equals(PaymentType.QRCODE) &&
            payment.getTotal().equals(new BigDecimal("99.99")) &&
            payment.getExternalId().equals(123L) &&
            payment.getId() == null
        ));
    }
}