package com.bytes.service.payment.application.useCases;

import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;
import com.bytes.service.payment.exceptions.ResourceNotFoundException;
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
class FindPaymentByOrderIdUseCaseTest {

    @Mock
    private PaymentRepositoryPort paymentRepository;

    private FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase;

    @BeforeEach
    void setUp() {
        findPaymentByOrderIdUseCase = new FindPaymentByOrderIdUseCase(paymentRepository);
    }

    @Test
    void shouldReturnPaymentWhenFound() {
        Long orderId = 100L;
        Payment expectedPayment = new Payment(1L, orderId, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        when(paymentRepository.findByOrderId(orderId)).thenReturn(Optional.of(expectedPayment));

        Payment result = findPaymentByOrderIdUseCase.execute(orderId);

        assertEquals(expectedPayment, result);
        verify(paymentRepository).findByOrderId(orderId);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenPaymentNotFound() {
        Long orderId = 100L;

        when(paymentRepository.findByOrderId(orderId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            findPaymentByOrderIdUseCase.execute(orderId);
        });

        assertEquals("Pagamento não encotrado para o pedido 100", exception.getMessage());
        verify(paymentRepository).findByOrderId(orderId);
    }
}