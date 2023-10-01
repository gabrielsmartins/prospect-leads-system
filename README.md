# Prospect Leads System

Este repositório contém um exemplo de plaforma de prospecção de leads seguros apresentado no Trabalho de Conclusão de Curso de Pós Graduação em Arquitetura de Software Distribuído da PUC Minas.

<img src="/docs/img/Overview.png" alt="Overview" title="Overview">

## Tecnologias

Este projeto utiliza das seguintes tecnologias:

- AWS API Gateway
- AWS RDS
- AWS DynamoDb
- AWS DocumentDb
- AWS ElastiCache Redis
- Spring Boot
- Spring Cloud
- Angular

## Rodando o projeto localmente

Para rodar as aplicações localmente siga as instruções abaixo.

Acesse a pasta `infra` e digite o seguinte comando:

```console
$ docker-compose up -d
```

Acesse a pasta app/backend e para aplicação execute o comando

```console
$ ./gradlew bootRun --args='--spring.profiles.active=dev'
```

Acesse a pasta app/frontend e para aplicação execute o comando

```console
$ ng serve
```

Abra no browser o endereço http://localhost:4200

