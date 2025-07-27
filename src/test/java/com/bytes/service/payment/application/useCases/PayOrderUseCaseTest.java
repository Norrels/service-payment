package com.bytes.service.payment.application.useCases;

import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayOrderUseCaseTest {

    @Mock
    private PaymentRepositoryPort paymentRepository;

    private PayOrderUseCase payOrderUseCase;

    @BeforeEach
    void setUp() {
        payOrderUseCase = new PayOrderUseCase(paymentRepository);
    }

    @Test
    void shouldExecuteWithoutErrors() {
        Long orderId = 100L;

        assertDoesNotThrow(() -> payOrderUseCase.execute(orderId));
    }

    @Test
    void shouldAcceptNullOrderId() {
        assertDoesNotThrow(() -> payOrderUseCase.execute(null));
    }
}