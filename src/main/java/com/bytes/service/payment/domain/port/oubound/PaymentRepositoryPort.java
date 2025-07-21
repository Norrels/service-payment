package com.bytes.service.payment.domain.port.oubound;

import com.bytes.service.payment.domain.models.Payment;

import java.util.Optional;

public interface PaymentRepositoryPort {
   Payment save(Payment payment);
   Optional<Payment> findByOrderId(Long id);
}
