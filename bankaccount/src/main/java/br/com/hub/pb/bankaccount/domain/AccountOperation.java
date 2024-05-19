package br.com.hub.pb.bankaccount.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public enum AccountOperation {
    DEPOSIT {
        public void execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            if (operationAmount.signum() == 1) {
                final var currentBalance = accountBalance.getAmount();
                final var newBalance = currentBalance.add(operationAmount);

                accountBalance.updateBalanceAmount(newBalance);
            }
        }
    },
    WITHDRAW {
        public void execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            if (operationAmount.signum() == 1) {
                final var currentBalance = accountBalance.getAmount();
                final var newBalance = currentBalance.subtract(operationAmount);

                accountBalance.updateBalanceAmount(newBalance);
            }
        }
    };

    private static final Logger log = LoggerFactory.getLogger(AccountOperation.class);
    private static final String OPERATION_ERROR_MESSAGE = "could update balance";

    public abstract void execute(final AccountBalance accountBalance, final BigDecimal operationAmount);
}
