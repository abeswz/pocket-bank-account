# Pocket Bank account
This is a small digital bank account project.

Pocket bank account has only two types of users, common and companies users, all of them has a wallet balance and they can do money transfers between accounts. For this project in initial mode we focus only in the money transfer use case.

Requirements

-  For all types of user Procket Bank needs this information about, full name, document (CPF and CNPJ), e-mail and password. The document and e-mail must be unique in the system.
  
-   All can transfer money between accounts

-   Companies just receive money. They don't transfer money to any other wallet.

-  Validate user account balance before the money transfer operation.

-  Validate process through the authorization service before the end operation.

-  The transfer operation must be a transaction, this is included for all inconsistencies through the every operation could be reverted and all money must return to the initial operation user wallet.

-  For money receive process, the user ou company must receive a notification by email or sms, consider all implications for this type of service like disponibility issues or instability and offline operations.

### Architecture propose
For all solutions we build some architecture [take a look](architecture/ARCHITECTURE.md)

### Payload

This is an example of payload for a transaction

POST /transaction

```json
{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}
```
