# :hospital: Health - Cadastro de beneficiário plano de saúde :hospital:

## 💻 O projeto foi desenvolvido com:

- [x] Java 17
- [x] Spring Boot 2.5.2
- [x] Spring security
- [x] Token JWT
- [x] Swagger spring fox versão 3.0.0 - Após iniciar o projeto acesse: http://localhost:8080/swagger-ui/index.html
- [x] Banco de dados - MYSQL last version
- [x] Docker

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Você instalou a versão do Java`< JDK17 / requeridos>`
* Você instalou a versão`< DOCKER / requeridos>`

## 🚀 Executando

Após efetuar o clone do Back End adicione o POM do projeto ao Mavem e aguarde o download das dependências.

Em seguida via terminal navegue até a pasta raiz do projeto onde encontra-se o arquivo docker-compose.yml e execute o comando:

- [x] docker-compose -up

O Docker vai iniciar subindo um container com o MySQL,a pós isso, inicie o projeto pela classe main HealthApplication. 

A classe `DataInitializationConfig` será responsável por inserir no banco os usuários: `admin` e `manager`. O usuário admin terá a role_admin, enquanto o manager terá a role_manager. As senhas do admin e do manager é `123`, sendo esses usuários utilizados para obtenção do token JWT.

Após esse processo, acesse o swagger, utilize o método de login para obter o token. Vá em `authorize` e cole o token com o valor `Bearer`. Agora, você pode realizar as requisições que estão exemplificadas.
`O token tem uma duração de 5 minutos.`

Para encerrar o container, utilize o comando `docker-compose down`. Esse comando remove o container e o banco de dados.

No arquivo de propriedades, encontram-se os valores da `JWT secret` e o tempo de expiração do `JWT em milissegundos`.

A criação das tabelas é executada assim que o Spring é iniciado, com as devidas configurações de cardinalidade.

