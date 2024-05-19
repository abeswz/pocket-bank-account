package br.com.hub.pb.bankaccount.events.requested;

import br.com.hub.pb.bankaccount.application.repositories.AccountRepository;
import br.com.hub.pb.bankaccount.application.usecases.UpdateAccountBalanceUseCase;
import br.com.hub.pb.bankaccount.domain.AccountBalance;
import br.com.hub.pb.bankaccount.events.requested.implentation.TransferRequestEventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferRequestEventTest {

    @Mock
    private AccountRepository repository;

    private UpdateAccountBalanceUseCase useCase;

    private TransferRequestEvent event;

    @BeforeEach
    void setup() {
        this.event = new TransferRequestEventImpl(this.repository, new UpdateAccountBalanceUseCase());
    }

    @Test
    void shouldExecuteTransferRequest_whenDepositIsRequired() {
        when(repository.getAccountBalance("customer-id-1234", "customer-code-01"))
                .thenReturn(Optional.of(new AccountBalance("balance-1234", BigDecimal.TEN)));

        final var input = new TransferRequestEvent.Input(
                "1234",
                "customer-id-1234",
                "customer-code-01",
                BigDecimal.TEN,
                "DEPOSIT"
        );

        event.execute(input);

        verify(this.repository, times(1))
                .getAccountBalance(anyString(), anyString());

        verify(this.repository, times(1))
                .save(any(AccountBalance.class));
    }

    @Test
    void shouldValidateAccountBalanceAmount_whenDepositIsRequired() {
        when(repository.getAccountBalance("customer-id-1234", "customer-code-01"))
                .thenReturn(Optional.empty());

        final var input = new TransferRequestEvent.Input(
                "1234",
                "customer-id-1234",
                "customer-code-01",
                BigDecimal.TEN,
                "DEPOSIT"
        );

        assertThrows(TransferRequestEvent.AccountNotFoundException.class, () -> event.execute(input));
    }

    @Test
    void shouldValidateAccountBalanceAmount_whenInvalidOperationSent() {
        when(repository.getAccountBalance("customer-id-1234", "customer-code-01"))
                .thenReturn(Optional.of(new AccountBalance("balance-1234", BigDecimal.TEN)));

        final var input = new TransferRequestEvent.Input(
                "1234",
                "customer-id-1234",
                "customer-code-01",
                BigDecimal.TEN,
                "ABC"
        );

        assertDoesNotThrow(() -> event.execute(input));
    }

    @Test
    void shouldValidateAccountBalanceAmount_whenInvalidAmountOperationSent() {
        final var currentAmount = BigDecimal.valueOf(Long.MAX_VALUE);
        final var mockAccountBalance = new AccountBalance("balance-1234", currentAmount);
        when(repository.getAccountBalance("customer-id-1234", "customer-code-01"))
                .thenReturn(Optional.of(mockAccountBalance));

        final var input = new TransferRequestEvent.Input(
                "1234",
                "customer-id-1234",
                "customer-code-01",
                BigDecimal.valueOf(Long.MAX_VALUE),
                "DEPOSIT"
        );

        assertDoesNotThrow(() -> event.execute(input));
    }
}