package com.sqstodynamodbapi.infrastructure.repository;

import com.sqstodynamodbapi.domain.model.Payment;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableScan
public interface PaymentRepository extends CrudRepository<Payment, String> {

    List<Payment> findAll();
}
