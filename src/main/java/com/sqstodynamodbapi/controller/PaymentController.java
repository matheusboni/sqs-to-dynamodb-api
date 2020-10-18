package com.sqstodynamodbapi.controller;

import com.sqstodynamodbapi.domain.model.Payment;
import com.sqstodynamodbapi.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("{id}")
    public ResponseEntity<?> findPaymentById(@PathVariable String id) {
        return paymentService.findById(id).map(ResponseEntity::ok).orElseGet(() -> notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> findAllPayments() {
        List<Payment> payments = paymentService.findAll();

        return CollectionUtils.isEmpty(payments) ? notFound().build() : ok(payments);
    }

}
