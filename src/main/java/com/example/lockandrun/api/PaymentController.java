package com.example.lockandrun.api;

import com.example.lockandrun.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public String pay(String paymentId) {
        return paymentService.pay(paymentId);
    }

    @GetMapping("/get")
    public String getPayment(String paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
