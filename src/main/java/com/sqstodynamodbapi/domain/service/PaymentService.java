package com.sqstodynamodbapi.domain.service;

import com.sqstodynamodbapi.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Optional<Payment> findById(String id);
    List<Payment> findAll();
}
