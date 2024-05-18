package br.com.hub.pb.bankaccount.events.requested.implentation;

import br.com.hub.pb.bankaccount.events.requested.TransferRequestEvent;

import javax.inject.Named;
import java.util.Optional;

@Named
public class TransferRequestEventImpl implements TransferRequestEvent {
    
    @Override
    public Optional<Output> execute(Input input) {
        return Optional.empty();
    }
}
