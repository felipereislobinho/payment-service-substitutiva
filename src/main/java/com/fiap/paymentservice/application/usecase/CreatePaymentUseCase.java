package com.fiap.paymentservice.application.usecase;

import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.domain.enums.PaymentStatus;
import com.fiap.paymentservice.infrastructure.client.SaleClient;
import com.fiap.paymentservice.infrastructure.gateway.PaymentGateway;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final SaleClient saleClient;

    public CreatePaymentUseCase(PaymentGateway paymentGateway, SaleClient saleClient) {
        this.paymentGateway = paymentGateway;
        this.saleClient = saleClient;
    }

    public Payment execute(PaymentRequest request) {
        Payment payment = new Payment(request.getSaleId(), request.getAmount(), PaymentStatus.PENDING);
        Payment savedPayment = paymentGateway.save(payment);
        return savedPayment;
    }
}
