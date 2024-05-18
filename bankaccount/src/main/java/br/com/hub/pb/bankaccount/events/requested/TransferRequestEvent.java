package br.com.hub.pb.bankaccount.events.requested;

import java.math.BigDecimal;
import java.util.Optional;

@FunctionalInterface
public interface TransferRequestEvent {

    Optional<Output> execute(final Input input);

    public record Input(String key,
                        String customerId,
                        String customerCode,
                        BigDecimal amount,
                        String operation) {}
    public record Output() {}
}
