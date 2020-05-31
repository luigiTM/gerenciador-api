CREATE TABLE IF NOT EXISTS cliente (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome varchar(50) NOT NULL,
    cpf varchar(13) NOT NULL,
    data_nascimento DATE
    );

CREATE TABLE IF NOT EXISTS produto (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    sku VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(30) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    preco FLOAT NOT NULL,
    quantidade INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS pedido(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    cliente INT NOT NULL,
    totalCompra FLOAT NOT NULL,
    data_compra DATE NOT NULL,
    FOREIGN KEY (cliente) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS pedidoProduto(
	idPedidoProduto INT NOT NULL,
	idPedido INT NOT NULL,
    idProduto INT NOT NULL,
    PRIMARY KEY (idPedido,idProduto),
    FOREIGN KEY (idPedido) REFERENCES pedido(id),
    FOREIGN KEY (idProduto) REFERENCES produto(id)
)
    