package br.com.hub.pb.bankaccount.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AccountBalance {
    private static final Logger log = LoggerFactory.getLogger(AccountBalance.class);
    private final String id;
    private BigDecimal amount;

    public AccountBalance(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public void updateBalance(final AccountOperation operation, final BigDecimal operationAmount) throws Exception {
        this.validateByOperation(operation, operationAmount);
        operation.execute(this, operationAmount);
    }

    private void validateByOperation(final AccountOperation operation, final BigDecimal operationAmount) throws OperationAmountException {
        log.info("validate account balance operation: {} - {}", operation, operationAmount);
        switch (operation) {
            case DEPOSIT, WITHDRAW -> {
                if (operationAmount.signum() != 1) {
                    throw new OperationAmountException(operation);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void updateBalanceAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    private static class OperationAmountException extends Exception {
        private static final String AMOUNT_NOT_ALLOWED_FOR_OPERATION = "Amount not allowed for operation";

        public OperationAmountException(final AccountOperation operation) {
            log.error("{}: [{}]", AMOUNT_NOT_ALLOWED_FOR_OPERATION, operation.name());
        }
    }

    private static class OperationBalanceFailedException extends Exception {
        private static final String TRANSFER_OPERATION_CRITICAL_FAIL = "Transfer operation critical fail";

        public OperationBalanceFailedException() {
            super(TRANSFER_OPERATION_CRITICAL_FAIL);
        }
    }
}
