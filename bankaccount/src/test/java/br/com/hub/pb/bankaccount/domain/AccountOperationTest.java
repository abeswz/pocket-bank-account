package br.com.hub.pb.bankaccount.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountOperationTest {

    @Test
    void shouldAddAmount_WhenDepositOperationRequested() {
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
    void shouldSubtractAmount_WhenWithdrawOperationRequested() {
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
    void shouldSubtract_WithAccountBalanceNegative() {
        final var operation = AccountOperation.WITHDRAW;
        final var balance = new AccountBalance("1234", BigDecimal.valueOf(-10L));

        final var currentBalance = balance.getAmount();
        final var id = balance.getId();

        operation.execute(balance, BigDecimal.TEN);

        Assertions.assertEquals(BigDecimal.valueOf(-20), balance.getAmount());
        Assertions.assertEquals(id, balance.getId());
        Assertions.assertNotEquals(currentBalance, balance.getAmount());
    }

    @Test
    void shouldNotSubtract_WithAccountBalanceNegative() {
        final var operation = AccountOperation.WITHDRAW;
        final var balance = new AccountBalance("1234", BigDecimal.TEN);

        final var currentBalance = balance.getAmount();
        final var id = balance.getId();

        operation.execute(balance, BigDecimal.valueOf(-100));

        Assertions.assertEquals(BigDecimal.TEN, balance.getAmount());
        Assertions.assertEquals(id, balance.getId());
        Assertions.assertEquals(0, currentBalance.compareTo(balance.getAmount()));
    }

    @Test
    void shouldNotAdd_WithAccountBalanceNegative() {
        final var operation = AccountOperation.DEPOSIT;
        final var balance = new AccountBalance("1234", BigDecimal.TEN);

        final var currentBalance = balance.getAmount();
        final var id = balance.getId();

        operation.execute(balance, BigDecimal.valueOf(-100));

        Assertions.assertEquals(BigDecimal.TEN, balance.getAmount());
        Assertions.assertEquals(id, balance.getId());
        Assertions.assertEquals(0, currentBalance.compareTo(balance.getAmount()));
    }

    @Test
    void shouldGetAccountOperationByText() {
        final var deposit = "DEPOSIT";
        final var operationDeposit = AccountOperation.valueOf(deposit);

        final var withdraw = "WITHDRAW";
        final var operationWithdraw = AccountOperation.valueOf(withdraw);

        Assertions.assertEquals(AccountOperation.DEPOSIT, operationDeposit);
        Assertions.assertEquals(AccountOperation.WITHDRAW, operationWithdraw);
    }
}