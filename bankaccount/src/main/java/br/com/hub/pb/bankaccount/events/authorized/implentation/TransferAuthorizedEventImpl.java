package br.com.hub.pb.bankaccount.events.authorized.implentation;

import br.com.hub.pb.bankaccount.events.authorized.TransferAuthorizedEvent;

import javax.inject.Named;
import java.util.Optional;

@Named
public class TransferAuthorizedEventImpl implements TransferAuthorizedEvent {

    @Override
    public Optional<Output> execute(Input input) {
        return Optional.empty();
    }
}
