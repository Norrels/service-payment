package com.bytes.service.payment.application.useCases;

import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;

public class PayOrderUseCase {

    private final PaymentRepositoryPort paymentRepository;

    public PayOrderUseCase(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepository = paymentRepositoryPort;
    }

    public void execute(Long orderId){}

}
