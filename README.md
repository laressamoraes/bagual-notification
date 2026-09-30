# notification
Microsserviço de notificações do Bagual Bank: notificações assíncronas sobre transações realizadas

## Sobre o serviço
Escuta o `transacoes` no Kafka, publicado pelo `transactions` sempre que uma transação é concluída, ou falha. Ao receber um evento monta uma mensagem descritiva e persiste um registro de notificação (simulando o envio de um aviso ao cliente) por e-mail, ou SMS, sem de fato enviar externamente.

A comunicação com o `transaction` é assíncrona: o `notification` não é chamado diretamente via REST, processando os eventos no seu próprio ritmo, sem impactar o fluxo principal de criação de transações.

## Funcionalidades implementadas
* Consumo de eventos de transação via Kafka
* Persistência de histórico de notificações
* Buscar notificação por id (`GET /notifications/{id}`)
* Listar notificações (`GET /notifications`)
* Tratamento centralizado de erros
* Migração de schema com Flyway
* Testes unitários (consumer e service layer)
* Containerização completa (aplicação + banco + Kafka via Docker Compose)

## Tecnologias
- Java 21 + Spring Boot 3;
- Maven;
- PostgreSQL;
- Flyway;
- Apache Kafka (Spring Kafka);
- JUnit 5, Mockito e AssertJ;
- Docker e Docker Compose.

## Decisões técnicas
* **Comunicação assíncrona via Kafka:** desacopla `transaction` e `notification`. Mesmo que o `notification` esteja indisponível, o `transaction` continua funcionando normalmente;
* **Persistência do histórico:** além de consumir o evento, o serviço registra a notificação no banco, permitindo a consulta posterior via API;
* **`@JsonCreator`/`@JsonProperty` no evento:** garante a desserialização correta do `TransactionEvent`, independente de configuração de compilador (evita depender da flag `-parameters`, que nem sempre é aplicada ao rodar pela IDE);
* **Kafka compartilhado com o `transaction` via rede Docker externa:** o broker roda no `docker-compose.yml` do `transaction`, o `notification` se conecta a ele através da rede `bagual-network`.

## Como executar

Pré-requisito: Docker Desktop instalado e em execução, e o [transaction](https://github.com/laressamoraes/bagual-transaction) rodando.

```bash
docker compose up --build -d
```

A API fica disponível em: http://localhost:8083

## Rodando os testes

```bash
mvn test
```
