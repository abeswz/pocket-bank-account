package br.com.hub.pb.bankaccount.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public enum AccountOperation {
    DEPOSIT("DEPOSIT") {
        public boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            try {
                final var currentBalance = accountBalance.getAmount();
                final var newBalance = currentBalance.add(operationAmount);

                accountBalance.setAmount(newBalance);
                return true;
            } catch (Exception e) {
                log.error("{} - [operation:{}]", OPERATION_ERROR_MESSAGE, DEPOSIT.name());
                return false;
            }
        }
    },
    WITHDRAW("WITHDRAW") {
        public boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            try {
                final var currentBalance = accountBalance.getAmount();
                final var newBalance = currentBalance.subtract(operationAmount);

                accountBalance.setAmount(newBalance);
                return true;
            } catch (Exception e) {
                log.error("{} - [operation:{}]", OPERATION_ERROR_MESSAGE, WITHDRAW.name());
                return false;
            }
        }
    },
    UPDATE("UPDATE") {
        public boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount) {
            try {
                if (operationAmount.signum() == 1) {
                    return DEPOSIT.execute(accountBalance, operationAmount);
                }

                if (operationAmount.signum() < 0) {
                    return WITHDRAW.execute(accountBalance, operationAmount);
                }

                return false;
            } catch (Exception e) {
                log.error("{} - [operation:{}]", OPERATION_ERROR_MESSAGE, UPDATE.name());
                return false;
            }
        }
    };

    private static final Logger log = LoggerFactory.getLogger(AccountOperation.class);
    private static final String OPERATION_ERROR_MESSAGE = "could update balance";
    protected final String operation;

    AccountOperation(String operation) {
        this.operation = operation;
    }

    public abstract boolean execute(final AccountBalance accountBalance, final BigDecimal operationAmount);
}
