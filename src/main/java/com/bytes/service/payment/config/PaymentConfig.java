package com.bytes.service.payment.config;

import com.bytes.service.payment.application.PaymentService;
import com.bytes.service.payment.application.useCases.CreatePaymentUseCase;
import com.bytes.service.payment.application.useCases.FindPaymentByOrderIdUseCase;
import com.bytes.service.payment.application.useCases.PayOrderUseCase;
import com.bytes.service.payment.domain.port.oubound.PaymentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentRepositoryPort paymentRepositoryPort){
        return new CreatePaymentUseCase(paymentRepositoryPort);
    }
    @Bean
    public FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase(PaymentRepositoryPort paymentRepositoryPort){
        return new FindPaymentByOrderIdUseCase(paymentRepositoryPort);
    }

    @Bean
    public PayOrderUseCase payOrderUseCase(PaymentRepositoryPort paymentRepositoryPort) {
        return new PayOrderUseCase(paymentRepositoryPort);
    }

    @Bean
    public PaymentService paymentService(CreatePaymentUseCase createPaymentUseCase, FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase, PayOrderUseCase payOrderUseCase) {
        return new PaymentService(createPaymentUseCase, findPaymentByOrderIdUseCase, payOrderUseCase);
    }
}
