package br.com.hub.pb.bankaccount.events.authorized;

import java.util.Optional;

@FunctionalInterface
public interface TransferAuthorizedEvent {

    Optional<Output> execute(Input input);

    public record Input(String id, String name) {}
    public record Output() {}
}