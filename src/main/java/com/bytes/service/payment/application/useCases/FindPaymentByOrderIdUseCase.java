package com.bytes.service.payment.application.useCases;

import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;
import com.bytes.service.payment.exceptions.ResourceNotFoundException;

public class FindPaymentByOrderIdUseCase {
    private final PaymentRepositoryPort paymentRepository;

    public FindPaymentByOrderIdUseCase(PaymentRepositoryPort paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment execute(Long orderId) {
       return paymentRepository.findByOrderId(orderId).orElseThrow(() -> new ResourceNotFoundException("Pagamento não encotrado para o pedido " + orderId));
    }
}
