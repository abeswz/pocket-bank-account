package br.com.hub.pb.bankaccount.application.usecases;

import br.com.hub.pb.bankaccount.domain.AccountBalance;
import br.com.hub.pb.bankaccount.domain.AccountOperation;

import java.math.BigDecimal;

public class UpdateAccountBalanceUseCase {

    public Output execute(final Input input) throws Exception {
        final var accountBalance = input.accountBalance;
        accountBalance.updateBalance(input.operation, input.amount);
        return new Output(accountBalance);
    }

    public record Input(AccountBalance accountBalance,
                        BigDecimal amount,
                        AccountOperation operation) {
    }

    public record Output(AccountBalance accountBalance) {
    }
}
