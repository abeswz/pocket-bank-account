package br.com.hub.pb.bankaccount.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public enum AccountOperation {
    DEPOSIT {
        public boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            try {
                final var currentBalance = accountBalance.getAmount();
                final var newBalance = currentBalance.add(operationAmount);

                accountBalance.updateBalanceAmount(newBalance);
                return true;
            } catch (Exception e) {
                log.error("{} - [operation:{}]", OPERATION_ERROR_MESSAGE, DEPOSIT.name());
                return false;
            }
        }
    },
    WITHDRAW {
        public boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            try {
                final var currentBalance = accountBalance.getAmount();
                final var newBalance = currentBalance.subtract(operationAmount);

                accountBalance.updateBalanceAmount(newBalance);
                return true;
            } catch (Exception e) {
                log.error("{} - [operation:{}]", OPERATION_ERROR_MESSAGE, WITHDRAW.name());
                return false;
            }
        }
    };

    private static final Logger log = LoggerFactory.getLogger(AccountOperation.class);
    private static final String OPERATION_ERROR_MESSAGE = "could update balance";

    public abstract boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount);
}
