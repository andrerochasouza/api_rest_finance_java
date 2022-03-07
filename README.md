# finance.io API

O [Finance.io](https://github.com/andrerochasouza/Finance.io-API) conta com um REST API para controle de dados entre um usuário, carteira do usuário, e as aplicações da carteira.

Recursos disponíveis para acesso via API:
* [**Banco de dados**](#reference/recursos/produtos)
* [**Admin**](#reference/recursos/admin)


## Métodos
Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `GET` | Retorna informações de um ou mais registros. |
| `POST` | Utilizado para criar um novo registro. |
| `PUT` | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema. |


## Respostas

| Código | Descrição |
|---|---|
| `200` | Requisição executada com sucesso (success).|
| `202` | Requisição aceitada com sucesso (accepted).|
| `400` | Erros de validação ou os campos informados não existem no sistema.|
| `403` | Erro na autorização de Token Auth0 da API|
| `404` | Registro pesquisado não encontrado (Not found).|

Por questões de segurança, todas as requisições devem ser feitas através do protocolo `HTTPS`.

## Listar
As ações de `listar` permitem o envio dos seguintes parâmetros:

| Parâmetro | Descrição |
|---|---|
| `limit` | Filtra dados pelo valor informado. |
| `page` | Informa qual página deve ser retornada. |


# Group Autenticação - OAuth

Nossa API utiliza [OAuth0](https://auth0.com/pt) como forma de autenticação/autorização.


# Group Recursos

### Banco de dados [MySQL]

Necessário tem instalado e configurado o MySQL 8.0, assim após a criação, criar um Schema com o nome *finance_io*.

Após, iniciar API REST eu seu computador e verificar se foi criado todas as tabelas do *Finance_io*.

### Admin [/new-admin]

Para acesso ao API, é necessário fazer uma requisição [POST] e se cadastrar utilizando o endpoint [/new-admin].

+ Request (application/json)

      {
          "name": "[Seu nome]",
          "email": "[Seu e-mail]",
          "login": "[Seu login]",
          "password": "[Sua senha]"
      }

+ Response 202 (application/json)

      {
          "id": 1,
          "name": "[Seu nome]",
          "email": "[Seu e-mail]",
          "login": "[Seu login]"
      }

*Read-Me em construção, obrigado*
