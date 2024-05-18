package br.com.hub.pb.bankaccount.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountOperationTest {

    @Test
    void shouldDepositAmountWhenDepositOperationRequested() {
        final var operation = AccountOperation.DEPOSIT;
        final var balance = new AccountBalance("1234", BigDecimal.TEN);

        final var currentBalance = balance.getAmount();
        final var id = balance.getId();

        operation.execute(balance, BigDecimal.TEN);

        Assertions.assertEquals(BigDecimal.valueOf(20L), balance.getAmount());
        Assertions.assertEquals(id, balance.getId());
        Assertions.assertNotEquals(currentBalance, balance.getAmount());
    }

    @Test
    void shouldDepositAmountWhenWithdrawOperationRequested() {
        final var operation = AccountOperation.WITHDRAW;
        final var balance = new AccountBalance("1234", BigDecimal.TEN);

        final var currentBalance = balance.getAmount();
        final var id = balance.getId();

        operation.execute(balance, BigDecimal.TEN);

        Assertions.assertEquals(BigDecimal.ZERO, balance.getAmount());
        Assertions.assertEquals(id, balance.getId());
        Assertions.assertNotEquals(currentBalance, balance.getAmount());
    }

    @Test
    void shouldGetAccountOperationByText() {
        final var text = "DEPOSIT";
        final var operation = AccountOperation.valueOf(text);

        Assertions.assertEquals(AccountOperation.DEPOSIT, operation);
    }
}