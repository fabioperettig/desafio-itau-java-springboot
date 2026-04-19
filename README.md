![Static Badge](https://img.shields.io/badge/Challenge-ffffff?style=for-the-badge)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Static Badge](https://img.shields.io/badge/Ita%C3%BA-ff6200?style=for-the-badge)

# Desafio Itaú Backend

Criar uma API REST que receba Transações e retorne Estatísticas sob essas transações. Para este desafio, a API foi criada utilizando Java e Spring Boot, seguindo regras predeterminadas. Confira os detalhes do desafio [aqui](https://github.com/fabioperettig/desafio-itau-java-springboot/blob/main/REGRAS.md).

## ☕️ Estrutura

Para uma boa arquitetura de software, foi utilizada uma estrutura de diretórios em camadas, tornando as responsabilidades melhor separadas e o código mais legível.

- **Model:** armazena as classes que representam a estrutura de dados central da aplicação.

- **DTO (Data Transfer Object):** objetos responsáveis por transportar os dados entre as camadas e/ou entre a API e o cliente, onde 'TransactionRequest' recebe os dados do POST e StatisticsResponse envia o resultado das estatísticas. Garantindo assim que a API não exponha diretamente as entidades internas.

- **Service:** diretório que contém toda a regra de negócio da aplicação. É onde a lógica de processamento ocorre (filtragem das transações e cálculo das estatísticas), isolando essas operações dos endpoints HTTP.

- **Controller:** a porta de entrada da API, com dois controllers distintos ('TransactionController' e 'StatisticsController') para gerenciar as requisições HTTP, delegando a execução para a camada de serviço e retornando as respostas adequadas de ***'status codes HTTP'***.

<br>

````java
java/desafio/itau/springboot
|
├── controller
|   ├── StatisticControler.java
|   └── TransactionControler.java
|
├── dto
|   ├── StatisticControler.java
|   └── TransactionControler.java
|
├── model
|   └── Transaction.java
|
├── service
|   └── TransactionService.java
|
├── SpringbootApplication.java
└── test.http
````

## Padrão Data Transfer Object
Neste projeto, o padrão DTO permite a comunicação entre a API e as camadas internas da aplicação e traz algumas vantagens ao uní-la com Springboot Validation:

- Desacoplamento: separa o contrato da API do modelo de domínio (Model). Isso permite que eu os nomes de campos ou formatos de JSON possaqm ser alteradosn sem impactar a lógica de negócio.

- Segurança e Validação: o pacote spring-boot-starter-validation contém anotações ( @NotNull e @Min) que garantem a chegadaa íntegra dos dados à aplicação antes mesmo de serem processados.

>Ao receber uma requisição, o Controller recebe um TransactionRequest (DTO), que é convertido para uma instância de Transaction (Model) na camada de serviço, garantindo a integridade dos dados e o isolamento das responsabilidades.

````java
public class TransactionDTO {

    @NotNull
    private Double valor;

    @NotNull
    private OffsetDateTime dataHora;

    public Double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}

-----

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void clearTransaction(){
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistic(){
        OffsetDateTime now = OffsetDateTime.now();
            return transactions.stream()
                    .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                    .mapToDouble(Transaction::getValor)
                    .summaryStatistics();
    }
}
````

## 🚀 Como testar

Siga os passos abaixo para ter a aplicação rodando na sua máquina:

1. Clone o repositório
Abra o seu terminal e execute o seguinte comando:
````bash
git clone https://github.com/fabioperettig/desafio-itau-java-springboot.git
````

2. Entre no diretório do projeto
````bash
cd nome-do-repositorio
````

3. Como este projeto utiliza Spring Boot com Maven, você pode subir o servidor localmente com o comando:
```bash
./mvnw spring-boot:run
```
>Certifique-se de ter o Java 17 instalado em sua máquina.

- Atente-se também às dependências necessárias para o projeto: 
````xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
````

## 🧑‍🚀 POSTMAN
A IDE utilizada no projeto foi **'IntelliJ Community'**, que não possui o cliente HTTP nativo, em comparação com a versão Ultimate. Recomendo utilizar o Postman para enviar os payloads JSON aos endpoints da API do seu teste.

**POST** '/transacao': Recebe uma transação.

**DELETE** '/transacao': Limpa a memória.

**GET** '/estatistica': Retorna os dados calculados.

````JSON
	### Criar Transação
	POST http://localhost:8080/transacao
	Content-Type: application/json

{
	
	"valor": 1337.00,
	"dataHora": "2026-01-01T20:15:10.789-03:00"
}
````

## 💡 Aprendizados e Conclusões
Realizar este desafio foi uma oportunidade excelente para consolidar conceitos essenciais de engenharia e arquitetura de software:

- DTO Pattern: a importância de desacoplar o contrato da API das regras de negócio, garantindo um código otimizado.
- Arquitetura em Camadas: o projeto revela a importância sobre a separação de responsabilidades entre Controller, Service e Model.
- Validações e HTTP: reforça a prática do uso de Validation e o retorno correto de códigos de status HTTP para uma API RESTful.

-----

### Fabio Peretti Guimarães | Abril de 2026