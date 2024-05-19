package br.com.hub.pb.bankaccount.events.requested;

import java.math.BigDecimal;

@FunctionalInterface
public interface TransferRequestEvent {

    void execute(final Input input);

    public record Input(String key,
                        String customerId,
                        String customerCode,
                        BigDecimal amount,
                        String operation) {
    }

    public static class AccountNotFoundException extends RuntimeException {
        private static final String ACCOUNT_NOT_FOUND_MESSAGE = "account not found";

        public AccountNotFoundException() {
            super(ACCOUNT_NOT_FOUND_MESSAGE);
        }
    }
}
