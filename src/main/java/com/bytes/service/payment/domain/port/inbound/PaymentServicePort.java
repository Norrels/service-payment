package com.bytes.service.payment.domain.port.inbound;

import com.bytes.service.payment.domain.models.Payment;

public interface PaymentServicePort {
    Payment create(Payment payment);
    Payment findByOrderId(Long orderId);
}
