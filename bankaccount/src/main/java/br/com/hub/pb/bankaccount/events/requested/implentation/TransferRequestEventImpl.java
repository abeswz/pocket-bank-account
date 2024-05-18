package br.com.hub.pb.bankaccount.events.requested.implentation;

import br.com.hub.pb.bankaccount.application.usecases.ValidateTransferUseCase;
import br.com.hub.pb.bankaccount.domain.AccountOperation;
import br.com.hub.pb.bankaccount.events.requested.TransferRequestEvent;

import javax.inject.Named;
import java.util.Optional;

@Named
public class TransferRequestEventImpl implements TransferRequestEvent {
    private final ValidateTransferUseCase validateTransferUseCase;

    public TransferRequestEventImpl(final ValidateTransferUseCase validateTransferUseCase) {
        this.validateTransferUseCase = validateTransferUseCase;
    }

    @Override
    public Optional<Output> execute(final Input input) {
        final var inputToValidate = new ValidateTransferUseCase.Input(
                input.key(),
                input.customerId(),
                input.customerCode(),
                input.amount(),
                AccountOperation.valueOf(input.operation())
        );
        try {
            this.validateTransferUseCase.execute(inputToValidate);
            //TODO: Send Notification for authorized Operation
            return Optional.empty();
        } catch (Exception e) {
            //TODO: Send to DLQ Transfer Operation With Key
            return Optional.empty();
        }
    }
}
