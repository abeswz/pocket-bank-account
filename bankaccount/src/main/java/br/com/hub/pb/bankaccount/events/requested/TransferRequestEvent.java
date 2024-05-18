package br.com.hub.pb.bankaccount.events.requested;

import java.util.Optional;

@FunctionalInterface
public interface TransferRequestEvent {

    Optional<Output> execute(final Input input);

    public record Input() {}
    public record Output() {}
}
