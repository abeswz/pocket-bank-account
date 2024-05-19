package br.com.hub.pb.bankaccount.events.requested.implentation;

import br.com.hub.pb.bankaccount.application.repositories.AccountRepository;
import br.com.hub.pb.bankaccount.application.usecases.UpdateAccountBalanceUseCase;
import br.com.hub.pb.bankaccount.domain.AccountOperation;
import br.com.hub.pb.bankaccount.events.requested.TransferRequestEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
public class TransferRequestEventImpl implements TransferRequestEvent {

    private static final Logger log = LoggerFactory.getLogger(TransferRequestEventImpl.class);
    private final AccountRepository repository;
    private final UpdateAccountBalanceUseCase updateAccountBalanceUseCase;

    public TransferRequestEventImpl(AccountRepository repository, final UpdateAccountBalanceUseCase updateAccountBalanceUseCase) {
        this.repository = repository;
        this.updateAccountBalanceUseCase = updateAccountBalanceUseCase;
    }

    @Override
    public void execute(final Input input) {
        final var accountBalance = this.repository
                .getAccountBalance(input.customerId(), input.customerCode())
                .orElseThrow(TransferRequestEvent.AccountNotFoundException::new);
        try {
            final var toUpdateBalance = new UpdateAccountBalanceUseCase.Input(
                    accountBalance,
                    input.amount(),
                    AccountOperation.valueOf(input.operation())
            );
            final var output = this.updateAccountBalanceUseCase.execute(toUpdateBalance);
            repository.save(output.accountBalance());

            //TODO: Send Notification for authorized Operation
        } catch (IllegalArgumentException e) {
            log.error("illegal operation requested: {}", input.operation());
            //TODO: Add DLQ case invalid operation
        } catch (Exception e) {
            //TODO: Add DLQ critical case
        }
    }
}
