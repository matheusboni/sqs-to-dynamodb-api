package com.sqstodynamodbapi.infrastructure.queue.route;

import com.sqstodynamodbapi.domain.model.Payment;
import com.sqstodynamodbapi.domain.processor.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRouterBuilder extends RouteBuilder {

    private final PaymentProcessor paymentProcessor;

    @Override
    public void configure() throws Exception {
        from("{{amazon.queues.payment-queue}}")
                .unmarshal()
                .json(JsonLibrary.Jackson, Payment.class)
                .process(paymentProcessor);
    }
}
