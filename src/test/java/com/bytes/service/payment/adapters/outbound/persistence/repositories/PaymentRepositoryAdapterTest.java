package com.bytes.service.payment.adapters.outbound.persistence.repositories;

import com.bytes.service.payment.adapters.outbound.persistence.entities.PaymentEntity;
import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import com.bytes.service.payment.mapper.PaymentMapper;
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
class PaymentRepositoryAdapterTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper paymentMapper;

    private PaymentRepositoryAdapter paymentRepositoryAdapter;

    @BeforeEach
    void setUp() {
        paymentRepositoryAdapter = new PaymentRepositoryAdapter(paymentRepository, paymentMapper);
    }

    @Test
    void shouldSavePaymentSuccessfully() {
        Payment inputPayment = new Payment(null, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);
        PaymentEntity paymentEntity = new PaymentEntity();
        PaymentEntity savedEntity = new PaymentEntity();
        savedEntity.setId(1L);
        Payment savedPayment = new Payment(1L, 100L, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        when(paymentMapper.toPaymentEntity(inputPayment)).thenReturn(paymentEntity);
        when(paymentRepository.save(paymentEntity)).thenReturn(savedEntity);
        when(paymentMapper.toPayment(savedEntity)).thenReturn(savedPayment);

        Payment result = paymentRepositoryAdapter.save(inputPayment);

        assertEquals(savedPayment, result);
        verify(paymentMapper).toPaymentEntity(inputPayment);
        verify(paymentRepository).save(paymentEntity);
        verify(paymentMapper).toPayment(savedEntity);
    }

    @Test
    void shouldFindPaymentByOrderIdWhenExists() {
        Long orderId = 100L;
        PaymentEntity paymentEntity = new PaymentEntity();
        Payment payment = new Payment(1L, orderId, PaymentType.QRCODE, new BigDecimal("99.99"), 123L);

        when(paymentRepository.findByOrderId(orderId)).thenReturn(Optional.of(paymentEntity));
        when(paymentMapper.toPayment(paymentEntity)).thenReturn(payment);

        Optional<Payment> result = paymentRepositoryAdapter.findByOrderId(orderId);

        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
        verify(paymentRepository).findByOrderId(orderId);
        verify(paymentMapper).toPayment(paymentEntity);
    }

    @Test
    void shouldReturnEmptyWhenPaymentNotFoundByOrderId() {
        Long orderId = 100L;

        when(paymentRepository.findByOrderId(orderId)).thenReturn(Optional.empty());

        Optional<Payment> result = paymentRepositoryAdapter.findByOrderId(orderId);

        assertFalse(result.isPresent());
        verify(paymentRepository).findByOrderId(orderId);
        verify(paymentMapper, never()).toPayment(any());
    }

    @Test
    void shouldHandleMapperReturningNullInFindByOrderId() {
        Long orderId = 100L;
        PaymentEntity paymentEntity = new PaymentEntity();

        when(paymentRepository.findByOrderId(orderId)).thenReturn(Optional.of(paymentEntity));
        when(paymentMapper.toPayment(paymentEntity)).thenReturn(null);

        Optional<Payment> result = paymentRepositoryAdapter.findByOrderId(orderId);

        assertFalse(result.isPresent());
        verify(paymentRepository).findByOrderId(orderId);
        verify(paymentMapper).toPayment(paymentEntity);
    }
}