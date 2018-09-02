## Sobre

Aplicação desenvolvida com foco no back-end, utilizando JAX-RS com Jersey, H2 em memória, HikariCP, MentaBean (ORM), MentaContainer e Jetty como servidor de aplicação embarcado. O front-end está incluso nesta aplicação, e para tal foi utilizado VueJS pela simplicidade, com Bootstrap para permitir responsividade.

Nos testes unitários foi utilizado JUnit e Hamcrest. Para os testes de integração, além das bibliotecas citadas, foi utilizado o container Grizzly, onde cada teste sobe a aplicação REST em uma porta separada entregue pelo SO. Assim o teste fica o mais próximo possível de uma execução em produção.

Apesar de ser uma aplicação simples, o back-end está preparado para se conectar em um banco mais robusto, tal como PostgreSQL ou Oracle, apenas criando outra implementação para a classe abstrata `ConnectionManager` (vide `H2MemoryConnectionManager`). Como ORM foi utilizado MentaBean pela simplicidade e foco na configuração programática. Além disso, a aplicação possui documentação interativa (Swagger) para consulta e teste dos endpoints REST, bem como controle de pool de conexões (HikariCP) e injeção de dependência/IoC (MentaContainer).

## Rodando a aplicação

Para rodar, basta executar:

```shell
mvn jetty:run
```

Após isso, o acesso à interface web e à documentação interativa dos serviços (swagger), estarão disponíveis, respectivamente, nos endereços `http://localhost:8080` e `http://localhost:8080/docs`.

## Exercício 2

Está contido em formato zip, em `docs/Exercicio2Resolvido.zip`, contendo a classe original, que foi modificada apenas nos comentários, explicando os problemas encontrados, e mais a classe que contém a refatoração e simplificação do código.
