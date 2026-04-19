![Static Badge](https://img.shields.io/badge/Challenge-ffffff?style=for-the-badge)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Static Badge](https://img.shields.io/badge/Ita%C3%BA-ff6200?style=for-the-badge)

# Desafio Itaú Backend

Criar uma API REST que receba Transações e retorne Estatísticas sob essas transações. Para este desafio, a API foi criada utilizando Java e Spring Boot, seguindo as seguintes regras:

- **Não deve** fazer fork de nenhum outro projeto;
- 1 commit por cada endpoint, feito sempre pelo mesmo usuário;
- **Não deve** utilizar quaisquer sistemas de banco de dados (MySQL, PostgreSQL, etc.);
- **Não deve** utilizar cache (Redis, Memcached, Infinispan, etc.);
- Deve armazenar todos os dados em memória;
- Deve aceitar e responder apenas com JSON.

## A API só aceitará transações que:

- Tenham os campos valor e **'dataHora'** preenchidos;
- Aconteça na data atual ou anterior; 
- Apenas valores >= 0;

## Como resposta, espera-se que o endpoint responda com os seguintes status HTTP:

`````java
-- 201 Created --
A transação foi aceita;

-- 422 Unprocessable Entity --
A transação não foi aceita por qualquer motivo (ex: 'transação com valor menor que 0');

-- 400 Bad Request --
A API não compreendeu a requisição do cliente;
`````

![Voltar ao README original](https://github.com/fabioperettig/desafio-itau-java-springboot/tree/main)