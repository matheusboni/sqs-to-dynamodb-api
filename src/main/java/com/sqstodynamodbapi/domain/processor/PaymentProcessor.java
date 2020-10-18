package com.sqstodynamodbapi.domain.processor;

import com.sqstodynamodbapi.domain.model.Payment;
import com.sqstodynamodbapi.domain.model.Status;
import com.sqstodynamodbapi.infrastructure.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class PaymentProcessor implements Processor {

    private final PaymentRepository paymentRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        try {

            Payment payment = exchange.getIn().getBody(Payment.class);

            if (Objects.nonNull(payment) && payment.getStatus().equals(Status.PROCESSED)
                    && Objects.nonNull(payment.getPurchaseId())) {

                populatePayment(payment);
                paymentRepository.save(payment);
                System.out.printf("Payment: %s was save", payment);

            } else {
                System.err.printf("Payment: %s was not processed successfully", payment);
            }

        } catch (Exception e) {
            System.err.println("Exception occurred: " + e);
            throw e;
        }
    }

    private void populatePayment(Payment payment) {
        payment.setId(UUID.randomUUID().toString());
        payment.setDate(now().toString());
    }
}
