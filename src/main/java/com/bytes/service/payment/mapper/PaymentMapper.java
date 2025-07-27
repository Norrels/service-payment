package com.bytes.service.payment.mapper;

import com.bytes.service.payment.adapters.outbound.persistence.entities.PaymentEntity;
import com.bytes.service.payment.domain.models.Payment;
import com.bytes.service.payment.domain.models.PaymentType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentEntity paymentEntity) {
        if ( paymentEntity == null ) {
            return null;
        }

        Long id = null;
        Long orderId = null;
        PaymentType paymentType = null;
        BigDecimal total = null;
        Long externalId = null;

        id = paymentEntity.getId();
        orderId = paymentEntity.getOrderId();
        paymentType = paymentEntity.getPaymentType();
        total = paymentEntity.getTotal();
        externalId = paymentEntity.getExternalId();

        Payment payment = new Payment( id, orderId, paymentType, total, externalId );

        return payment;
    }

    public PaymentEntity toPaymentEntity(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setId( payment.getId() );
        paymentEntity.setOrderId( payment.getOrderId() );
        paymentEntity.setPaymentType( payment.getPaymentType() );
        paymentEntity.setTotal( payment.getTotal() );
        paymentEntity.setCreatedAt( payment.getCreatedAt() );
        paymentEntity.setExternalId( payment.getExternalId() );

        return paymentEntity;
    }
}
