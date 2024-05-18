package br.com.hub.pb.bankaccount.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AccountBalance {
    private static final Logger log = LoggerFactory.getLogger(AccountBalance.class);
    private String id;
    private BigDecimal amount;

    public void updateBalance(final AccountOperation operation, final BigDecimal operationAmount) throws Exception {
        this.validateByOperation(operation, operationAmount);
        final var executed = operation.execute(this, operationAmount);

        if (!executed) {
            throw new Exception("balance operation failed");
        }
    }

    private void validateByOperation(final AccountOperation operation, final BigDecimal operationAmount) {
        log.info("validate account balance operation: {} - {}", operation, operationAmount);
        switch (operation) {
            case DEPOSIT, WITHDRAW -> {
                // Deposit and Transfer operation only allow positive operation amount
                if (operationAmount.signum() != 1) {
                    throw new OperationAmountException(operation);
                }
            }
            case UPDATE -> {
                // Update account doesn't allow zero operation
                if (operationAmount.signum() == 0) {
                    throw new OperationAmountException(operation);
                }
            }

        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    private static class OperationAmountException extends RuntimeException {
        private static final String AMOUNT_NOT_ALLOWED_FOR_OPERATION = "Amount not allowed for operation";

        public OperationAmountException(final AccountOperation operation) {
            log.error("{}: [{}]", AMOUNT_NOT_ALLOWED_FOR_OPERATION, operation.name());
        }
    }
}
