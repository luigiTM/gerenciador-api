# gerenciador-api

API para aplicativo de gerenciamento de clientes, produtos e pedidos.

Tecnologias utilizadas:\
-Sprint Tools Suite 4 com spring boot 2.3.0\
-Hibernate\
-MySQL

Para inicializar o banco de dados o script 'inicializarBase.sql' dentro da pasta 'sql' na raiz do projeto deve ser utilizado em um banco de dados vazio no MySQL.\
O arquivo application.properties deve ser configurado com a conexão ao banco de dados.\
Para a execução do projeto pode ser feita a importação diretamente no STS4.\
Para acessar a API basta acessar a URL `http://localhost:8080`.

A API conta com os seguintes endpoints:

### GET:
  /clientes - Retorna todos os clientes cadastrados no banco.\
  /clientes/{id} - Retorna o cliente que tenha o id fornecido.\
  /clientes/paginado?linhasPagina={l}&pagina={p}&direcao={d}&ordenacao={o} - Retorno os clientes de forma paginada, onde 'l' é o número de linhas por página(padrão 24), 'p' é o número da página, 'd' é a direção(padrão ASC), e 'o' é a ordenação(padrão campo nome).
  
  /produtos - Retorna todos os produtos.\
  /produtos/{id} - Retorna o produto que tenha o id fornecido.\
  /produtos/paginados?linhasPagina={l}&pagina={p}&direcao={d}&ordenacao={o} - Retorno os produtos de forma paginada, onde 'l' é o número de linhas por página(padrão 24), 'p' é o número da página, 'd' é a direção(padrão ASC), e 'o' é a ordenação(padrão campo nome).
  
  /pedidos\
  /pedidos/{id}\
  /pedidos/paginados?linhasPagina={l}&pagina={p}&direcao={d}&ordenacao={o} - Retorno os pedidos de forma paginada, onde 'l' é o número de linhas por página(padrão 24), 'p' é o número da página, 'd' é a direção(padrão ASC), e 'o' é a ordenação(padrão campo dataCompra).
  
### POST:
  /clientes - Usado para inserção de novos clientes.\
  Ex:{\
        "nome" : "Teste",\
        "cpf" : 12345678909,\
        "dataNascimento": "2000-01-01"(Opcional)\
      }\
  /produtos - Usado para inserção de novos clientes.\
  Ex:{\
	      "nome" : "produto",\
	      "sku" : "abc123456",\
	      "descricao" : "abcdef",\
	      "preco" : 1.2,\
	      "quantidade": 1\
      }\
  /pedidos - Usado para inserção de novos pedidos - Não finalizado.\
  Um erro personalizado indicando qual campo não passou na validação será exibido.
  
### PUT:
  /clientes/{id} - Usado para atualizar clientes com o id fornecido, segue o mesmo modelo da inserção.\
  /produtos/{id} - Usado para atualizar produtos com o id fornecido, segue o mesmo modelo da inserção.\
  /pedidos/{id} - Usado para atualizar pedidos com o id fornecido, segue o mesmo modelo da inserção - Não finalizado.
  
### DELETE:
  /clientes/{id} - Usado para remover clientes com o id fornecido.\
  /produtos/{id} - Usado para remover produtos com o id fornecido.\
  /pedidos/{id} - Usado para remover pedidos com o id fornecido.
