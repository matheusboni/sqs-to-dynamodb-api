package com.sqstodynamodbapi.domain.service.impl;

import com.sqstodynamodbapi.domain.model.Payment;
import com.sqstodynamodbapi.domain.service.PaymentService;
import com.sqstodynamodbapi.infrastructure.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> findById(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
