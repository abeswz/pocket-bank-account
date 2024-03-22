# Pocket Bank account
This is a small digital bank account project.

Pocket bank account has only two types of users, common and companies users, all of them has a wallet balance and they can do money transfers between accounts. For this project in initial mode we focus only in the money transfer use case.

Requirements

-  For all types of user Procket Bank needs this information about, full name, document (CPF and CNPJ), e-mail and password. The document and e-mail must be unique in the system.
  
-   All can transfer money between accounts

-   Companies just receive money. They don't transfer money to any other wallet.

-  Validate user account balance before the money transfer operation.

-    Validate process through the authorization service before the end operation.

-   A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) e o dinheiro deve voltar para a carteira do usuário que envia.

-   No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de email, sms) enviada por um serviço de terceiro e eventualmente este serviço pode estar indisponível/instável. Use este mock para simular o envio (https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6).

-   Este serviço deve ser RESTFul.

### Payload

Faça uma **proposta** :heart: de payload, se preferir, temos uma exemplo aqui:

POST /transaction

```json
{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}
```
