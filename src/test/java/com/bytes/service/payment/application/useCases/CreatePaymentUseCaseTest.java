package com.bytes.service.payment.application.useCases;

import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;
import com.bytes.service.payment.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePaymentUseCaseTest {

    @Mock
    private PaymentRepositoryPort paymentRepository;

    private CreatePaymentUseCase createPaymentUseCase;

    @BeforeEach
    void setUp() {
        createPaymentUseCase = new CreatePaymentUseCase(paymentRepository);
    }

    @Test
    void shouldCreatePaymentWhenOrderIdDoesNotExist() {
        Payment payment = new Payment(null, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        Payment savedPayment = new Payment(1L, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        when(paymentRepository.findByOrderId(100L)).thenReturn(Optional.empty());
        when(paymentRepository.save(payment)).thenReturn(savedPayment);

        Payment result = createPaymentUseCase.execute(payment);

        assertEquals(savedPayment, result);
        verify(paymentRepository).findByOrderId(100L);
        verify(paymentRepository).save(payment);
    }

    @Test
    void shouldThrowBusinessExceptionWhenPaymentAlreadyExistsForOrder() {
        Payment payment = new Payment(null, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        Payment existingPayment = new Payment(1L, 100L, PaymentType.QRCODE, new BigDecimal("50.00"), 456L);

        when(paymentRepository.findByOrderId(100L)).thenReturn(Optional.of(existingPayment));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            createPaymentUseCase.execute(payment);
        });

        assertEquals("Já existe um pagamento para o pedido: 100", exception.getMessage());
        verify(paymentRepository).findByOrderId(100L);
        verify(paymentRepository, never()).save(any());
    }
}