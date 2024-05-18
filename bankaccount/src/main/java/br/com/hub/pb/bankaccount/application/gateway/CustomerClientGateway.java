package br.com.hub.pb.bankaccount.application.gateway;

import java.util.Optional;

public interface CustomerClientGateway {

    Optional<Output> requestCustomer(final String id, final String code);

    public record Output(String customerId, String customerName, String customerCode) {
    }
}
