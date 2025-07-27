package com.bytes.service.payment.adapters.inbound.dtos;

import com.bytes.service.payment.domain.models.PaymentType;

import java.math.BigDecimal;

public class PaymentDTO {
        private Long order_id;
        private PaymentType paymentType;
        private BigDecimal total;
        private Long external_id;

        public Long getOrder_id() {
                return order_id;
        }

        public void setOrder_id(Long order_id) {
                this.order_id = order_id;
        }

        public PaymentType getPaymentType() {
                return paymentType;
        }

        public void setPaymentType(PaymentType paymentType) {
                this.paymentType = paymentType;
        }

        public BigDecimal getTotal() {
                return total;
        }

        public void setTotal(BigDecimal total) {
                this.total = total;
        }

        public Long getExternal_id() {
                return external_id;
        }

        public void setExternal_id(Long external_id) {
                this.external_id = external_id;
        }
}
