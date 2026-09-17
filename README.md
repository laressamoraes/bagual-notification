# bagual-bank
Sistema financeiro simplificado construído em arquitetura de microsserviços, simulando operações bancárias básicas como criação de conta, depósito, saque e transferências.

No Rio Grande do Sul, bagual é um termo que serve pra descrever um cavalo xucro, selvagem, não domado. Mas quando usado pra se referir a pessoas, representa coragem, resiliência e autenticidade.

# Sobre o projeto
Tem como objetivo aplicar os conceitos e ferramentas utilizados em sistemas corporativos de médio/grande porte: comunicação entre serviços, consistência de dados distribuídos, testes automatizados e containerização.

# Arquitetura
O sistema é dividido em microsserviços independentes:
| SERVIÇO | RESPONSABILIDADE | STATUS |
|---|---|---|
| account      | Cadastro de contas, consulta de saldo, débito/crédito | **Implementado** |
| transaction  | Depósitos, saques e transferências entre contas       | **Implementado** |
| notification | Notificações assíncronas sobre transações realizadas  | **Em andamento** |

# Tecnologias
- Java 21 + Spring Boot 3;
- Maven;
- Apache Kafka;
- PostgreSQL;
- Flyway;
- JUnit 5 e Mockito;
- Docker e Docker Compose.
