package com.bytes.service.payment.adapters.outbound.persistence.repositories;


import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;
import com.bytes.service.payment.mapper.PaymentMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentRepositoryAdapter(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentMapper.toPayment(paymentRepository.save(paymentMapper.toPaymentEntity(payment)));
    }

    @Override
    public Optional<Payment> findByOrderId(Long id) {
        return paymentRepository.findByOrderId(id).map(paymentMapper::toPayment);
    }
}
