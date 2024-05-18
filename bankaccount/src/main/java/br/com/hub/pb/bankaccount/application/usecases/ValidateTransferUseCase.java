package br.com.hub.pb.bankaccount.application.usecases;

import br.com.hub.pb.bankaccount.application.repositories.AccountRepository;
import br.com.hub.pb.bankaccount.domain.AccountOperation;
import br.com.hub.pb.bankaccount.infrastructure.configuration.annotations.UseCase;

import java.math.BigDecimal;

@UseCase
public class ValidateTransferUseCase {
    private final AccountRepository repository;

    public ValidateTransferUseCase(final AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(final Input input) throws Exception {
        final var accountBalance = this.repository.getAccountBalance(input.customerId, input.customerCode)
                .orElseThrow(AccountNotFoundException::new);

        accountBalance.updateBalance(input.operation, input.amount);
    }

    public record Input(String key,
                        String customerId,
                        String customerCode,
                        BigDecimal amount,
                        AccountOperation operation) {
    }

    public static class AccountNotFoundException extends RuntimeException {
        private static final String ACCOUNT_NOT_FOUND_MESSAGE = "CODE:1000 - Invalid Account";

        public AccountNotFoundException() {
            super(ACCOUNT_NOT_FOUND_MESSAGE);
        }
    }
}
