package br.com.hub.pb.bankaccount.application.repositories;

import br.com.hub.pb.bankaccount.domain.AccountBalance;

import java.util.Optional;

public interface AccountRepository {
    Optional<AccountBalance> getAccountBalance(final String customerId, final String customerCode);
}
