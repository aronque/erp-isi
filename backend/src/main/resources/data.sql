--TABELAS USUÁRIO
CREATE TABLE TIPO_USUARIO (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    TIPO VARCHAR(7),
    DESCRICAO VARCHAR(100),
    PRIMARY KEY(ID)
);

CREATE TABLE USUARIOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NOME VARCHAR(60),
    EMAIL VARCHAR(60),
    SENHA VARCHAR(18),
    TIPO_ID BIGINT,
    PRIMARY KEY(ID)
);

ALTER TABLE IF EXISTS USUARIOS ADD CONSTRAINT FK_TIPO_USUARIO FOREIGN KEY (TIPO_ID) REFERENCES TIPO_USUARIO;
------------------------------------------------------------------------------------------------------------------------
--TABELAS FORNECEDOR
CREATE TABLE ENDERECOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    RUA VARCHAR(40),
    NUMERO VARCHAR(10),
    BAIRRO VARCHAR(40),
    CIDADE VARCHAR(40),
    ESTADO_UF VARCHAR(2),
    CEP VARCHAR(9),
    PRIMARY KEY(ID)
);

CREATE TABLE FORNECEDORES (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NOME VARCHAR(60),
    CNPJ VARCHAR(14),
    CONTATO VARCHAR(60),
    ENDERECO_ID BIGINT,
    PRIMARY KEY(ID)
);

ALTER TABLE IF EXISTS FORNECEDORES ADD CONSTRAINT FK_END_FORNECEDORES FOREIGN KEY (ENDERECO_ID) REFERENCES ENDERECOS;

------------------------------------------------------------------------------------------------------------------------
--TABELAS PRODUTO
CREATE TABLE PRODUTOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NOME VARCHAR(60),
    QNT_ESTOQUE INT,
    PRECO FLOAT8,
    FORNECEDOR_ID BIGINT,
    PRIMARY KEY(ID)
);

ALTER TABLE IF EXISTS PRODUTOS ADD CONSTRAINT FK_FORN_PRODUTOS FOREIGN KEY (FORNECEDOR_ID) REFERENCES FORNECEDORES;

------------------------------------------------------------------------------------------------------------------------
--TABELAS PEDIDO
CREATE TABLE PEDIDOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    DATA_PEDIDO DATE,
    STATUS_PEDIDO VARCHAR(20),
    USUARIO_ID BIGINT,
	FORNECEDOR_ID BIGINT,
	DTYPE VARCHAR(20),
    PRIMARY KEY(ID)
);

ALTER TABLE IF EXISTS PEDIDOS ADD CONSTRAINT FK_USER_PEDIDOS FOREIGN KEY (USUARIO_ID) REFERENCES USUARIOS;
ALTER TABLE IF EXISTS PEDIDOS ADD CONSTRAINT FK_FORN_PEDIDOS FOREIGN KEY (FORNECEDOR_ID) REFERENCES FORNECEDORES;

CREATE TABLE ITEM_PEDIDO (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    QUANTIDADE INT,
    PEDIDO_ID BIGINT,
    PRODUTO_ID BIGINT,
    PRIMARY KEY(ID)
);

--ALTERAR PARA REFERENCIAR PEDIDO
ALTER TABLE IF EXISTS ITEM_PEDIDO ADD CONSTRAINT FK_PED_PRODUTOS FOREIGN KEY (PEDIDO_ID) REFERENCES PEDIDOS;
ALTER TABLE IF EXISTS ITEM_PEDIDO ADD CONSTRAINT FK_PROD_ITEM FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTOS;
------------------------------------------------------------------------------------------------------------------------

INSERT INTO TIPO_USUARIO VALUES(1, 'ESTOQUE', 'Funcionario do estoque com acesso limitado');
INSERT INTO TIPO_USUARIO VALUES(2, 'GERENTE', 'Funcionario nivel gerente do estoque com acesso total');
INSERT INTO TIPO_USUARIO VALUES(3, 'COMUM', ' Funcionario comum da empresa que possui acesso a abertura de pedido');
INSERT INTO TIPO_USUARIO VALUES(4, 'SUPORTE', 'Operador do suporte ao usuário do sistema');

INSERT INTO USUARIOS (NOME, EMAIL, SENHA, TIPO_ID)
VALUES
	('ADM', 'adm@gmail.com', 'admisierp', 2),
	('João Silva', 'joao.silva@gmail.com', 'joao123', 3),
	('Maria Oliveira', 'maria.oliveira@Gmail.com', 'maria456',3),
	('Pedro Santos', 'pedro.santos@gmail.com', 'pedro789',3),
	('Alberto Estoquista', 'alberto@empresa.com', 'alberto123',1),
	('Beto Suporte', 'beto@suporte.com', 'betinho666',4);

-- Inserir um novo endereço
INSERT INTO ENDERECOS (RUA, NUMERO, BAIRRO, CIDADE, ESTADO_UF, CEP)
VALUES
	('Rua das Flores', '428', 'Centro', 'Torrinha', 'SP', '12345-678'),
	('Avenida Principal', '111', 'Vila Central', 'Pinheiros', 'SP', '12345-000'),
	('Rua dos Bobos', '0', 'Jardim Bonito', 'Bauru', 'SP', '12345-666');

-- Inserir um novo fornecedor
INSERT INTO FORNECEDORES (NOME, CNPJ, CONTATO, ENDERECO_ID)
VALUES
	('Torrinha LTDA', '12345678901234', 'Contato 1', (SELECT ID FROM ENDERECOS WHERE RUA = 'Rua das Flores')),
	('Bauru Artigos', '56789012345678', 'Contato 2', (SELECT ID FROM ENDERECOS WHERE RUA = 'Rua dos Bobos')),
	('Pinheiros INC', '90123456789012', 'Contato 3', (SELECT ID FROM ENDERECOS WHERE RUA = 'Avenida Principal'));

-- Inserir produtos referenciando os fornecedores
INSERT INTO PRODUTOS (NOME, QNT_ESTOQUE, PRECO, FORNECEDOR_ID)
VALUES
	('Oculos', 99, 19.99, (SELECT ID FROM FORNECEDORES WHERE NOME = 'Torrinha LTDA')),
	('Cadeira', 99, 29.99, (SELECT ID FROM FORNECEDORES WHERE NOME = 'Bauru Artigos')),
	('Mesa', 99, 39.99, (SELECT ID FROM FORNECEDORES WHERE NOME = 'Pinheiros INC')),
	('Chaveiro', 99, 39.99, (SELECT ID FROM FORNECEDORES WHERE NOME = 'Bauru Artigos')),
	('Tapete', 99, 39.99, (SELECT ID FROM FORNECEDORES WHERE NOME = 'Pinheiros INC')),
	('Sofa', 99, 39.99, (SELECT ID FROM FORNECEDORES WHERE NOME = 'Pinheiros INC'));

-- Inserir pedidos
INSERT INTO PEDIDOS (DATA_PEDIDO, STATUS_PEDIDO, FORNECEDOR_ID, DTYPE)
VALUES
	(NOW()::DATE, 'CRIADO', 1, 'PedidoFornecedor'),
	(NOW()::DATE, 'CRIADO', 2, 'PedidoFornecedor'),
	(NOW()::DATE, 'PROCESSANDO', 3, 'PedidoFornecedor'),
	(NOW()::DATE, 'CRIADO', 2, 'PedidoFornecedor'),
	(NOW()::DATE, 'CRIADO', 2, 'PedidoFornecedor'),
	(NOW()::DATE, 'FINALIZADO', 3, 'PedidoFornecedor'),
	(NOW()::DATE, 'CRIADO', 1, 'PedidoFornecedor'),
	(NOW()::DATE, 'PROCESSADO', 2, 'PedidoFornecedor'),
	(NOW()::DATE, 'FINALIZADO', 2, 'PedidoFornecedor'),
	(NOW()::DATE, 'PROCESSADO', null, 'PedidoSaidaEstoque'),
	(NOW()::DATE, 'CRIADO', null, 'PedidoSaidaEstoque'),
	(NOW()::DATE, 'PROCESSADO', null, 'PedidoSaidaEstoque'),
	(NOW()::DATE, 'CRIADO', null, 'PedidoSaidaEstoque'),
	(NOW()::DATE, 'PROCESSANDO', null, 'PedidoSaidaEstoque'),
	(NOW()::DATE, 'FINALIZADO', null, 'PedidoSaidaEstoque');

--Inserir itens dos pedidos
INSERT INTO ITEM_PEDIDO(QUANTIDADE, PEDIDO_ID, PRODUTO_ID)
VALUES
	(10, 1, 1),
	(20, 2, 2),
	(30, 2, 4),
	(15, 3, 3),
	(25, 3, 6),
	(10, 4, 4),
	(26, 5, 2),
	(12, 6, 5),
	(11, 7, 1),
	(43, 8, 2),
	(32, 8, 4),
	(10, 9, 2),
	(16, 10, 1),
	(19, 10, 2),
	(32, 11, 5),
	(17, 12, 5),
	(35, 12, 6),
	(23, 12, 4),
	(7, 13, 2),
	(3, 13, 1),
	(16, 14, 4),
	(21, 15, 4);

------------------------------------------------------------------------------------------------------------------------


------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW VW_FORNECEDOR_PRODUTO (nome_fornecedor, nome_produto, quantidade_estoque, preco) AS
SELECT
    fornecedores.nome as nome_fornecedor,
    produtos.nome as nome_produto,
    produtos.qnt_estoque as quantidade_estoque,
    produtos.preco
FROM
    fornecedores,
    produtos
WHERE
    produtos.fornecedor_id = fornecedores.id
ORDER BY
    fornecedores.nome;


CREATE OR REPLACE VIEW VW_PRODUTO_ESTOQUE (nome_produto, quantidade_estoque) AS
SELECT
	produtos.nome as nome_produto,
	produtos.qnt_estoque as quantidade_estoque
FROM
	produtos
ORDER BY
	produtos.nome;


CREATE OR REPLACE VIEW VW_PEDIDO_PENDENTE (id_pedido, data_pedido, status_pedido) AS
SELECT
	pedidos.id as id_pedido,
	pedidos.data_pedido,
	pedidos.status_pedido
FROM
	pedidos
WHERE
	pedidos.status_pedido NOT LIKE 'FINALIZADO'
ORDER BY
	pedidos.data_pedido;


CREATE OR REPLACE VIEW VW_VENDAS_PRODUTO (nome_produto, nome_fornecedor, quantidade_vendas) AS
SELECT
	produtos.nome as nome_produto,
	fornecedores.nome as nome_fornecedor,
	items_produto.quantidade as quantidade_vendas
FROM
	produtos,
	fornecedores,
	(SELECT
	 	count(*) as quantidade,
	 	produto_id
	 FROM
	 	item_pedido,
	 	pedidos
	 WHERE
	 	pedidos.id = item_pedido.pedido_id
	 AND
	 	pedidos.dtype LIKE 'PedidoSaidaEstoque'
	 GROUP BY
	 	produto_id) items_produto
WHERE
	fornecedores.id = produtos.fornecedor_id
AND
	items_produto.produto_id = produtos.id;


CREATE OR REPLACE VIEW VW_HIST_ESTOQUE (data_operacao, tipo_operacao, id_produto, id_pedido, quantidade, usuario) AS
SELECT
	pedidos.data_pedido data_operacao,
	(CASE WHEN
		pedidos.dtype LIKE 'PedidoSaidaEstoque'
	THEN
		'RETIRADA'
	ELSE
		'ENTRADA'
	END) as tipo_operacao,
	item_pedido.produto_id as id_produto,
	item_pedido.pedido_id as id_pedido,
	item_pedido.quantidade as quantidade,
	usuarios.nome
FROM
	pedidos,
	item_pedido,
	usuarios,
	produtos
WHERE
	item_pedido.pedido_id = pedidos.id
AND
	produtos.id = item_pedido.produto_id
AND
	usuarios.id = pedidos.usuario_id
ORDER BY
	pedidos.data_pedido;


--VIEWS PARA GRÁFICOS
CREATE OR REPLACE VIEW VW_PRODUTOS_PEDIDOS (nome_produto, quantidade_pedidos) AS
SELECT
	produtos.nome as nome_produto,
	items_produto.quantidade as quantidade_pedidos
FROM
	produtos,
	fornecedores,
	pedidos,
	(SELECT
	 	count(*) as quantidade,
	 	produto_id
	 FROM
	 	item_pedido,
	 	pedidos
	 WHERE
	 	pedidos.id = item_pedido.pedido_id
	 AND
	 	pedidos.dtype LIKE 'PedidoSaidaEstoque'
	 GROUP BY
	 	produto_id) items_produto
WHERE
	fornecedores.id = produtos.fornecedor_id
AND
	items_produto.produto_id = produtos.id;


CREATE OR REPLACE VIEW VW_MOVIMENTACOES_MES (mes_data, quantidade_movimentacoes) AS
SELECT DISTINCT
	vw.data_operacao mes_data,
	count(*) quantidade_movimentacoes
FROM
	VW_HIST_ESTOQUE vw
GROUP BY
	vw.data_operacao
ORDER BY
	vw.data_operacao;


CREATE OR REPLACE VIEW VW_STATUS_PEDIDOS (status_pedido, quantidade_pedidos) AS
SELECT
	pedidos.status_pedido,
	count(*) as quantidade_pedidos
FROM
	pedidos
GROUP BY
	pedidos.status_pedido;