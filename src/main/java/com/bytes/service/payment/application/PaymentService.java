package com.bytes.service.payment.application;


import com.bytes.service.payment.application.useCases.CreatePaymentUseCase;
import com.bytes.service.payment.application.useCases.FindPaymentByOrderIdUseCase;
import com.bytes.service.payment.application.useCases.PayOrderUseCase;
import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.port.inbound.PaymentServicePort;

public class PaymentService implements PaymentServicePort {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase;
    private final PayOrderUseCase payOrderUseCase;

    public PaymentService(CreatePaymentUseCase createPaymentUseCase, FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase, PayOrderUseCase payOrderUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.findPaymentByOrderIdUseCase = findPaymentByOrderIdUseCase;
        this.payOrderUseCase = payOrderUseCase;
    }

    @Override
    public Payment create(Payment payment) {
        Payment newPayment = createPaymentUseCase.execute(payment);
        payOrderUseCase.execute(payment.getOrderId());
        return newPayment;
    }

    @Override
    public Payment findByOrderId(Long orderId) {
        return findPaymentByOrderIdUseCase.execute(orderId);
    }
}
