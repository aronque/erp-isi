DROP VIEW IF EXISTS vw_vendas_produto;
DROP VIEW IF EXISTS vw_movimentacoes_mes;
DROP VIEW IF EXISTS vw_produtos_pedidos;
DROP VIEW IF EXISTS VW_PEDIDO_PENDENTE;
DROP VIEW IF EXISTS VW_PRODUTO_ESTOQUE;
DROP VIEW IF EXISTS VW_FORNECEDOR_PRODUTO;
DROP VIEW IF EXISTS VW_STATUS_PEDIDOS;
DROP VIEW IF EXISTS vw_hist_estoque;


DROP TABLE IF EXISTS ITEM_PEDIDO;
DROP TABLE IF EXISTS PEDIDOS;
DROP TABLE IF EXISTS PRODUTOS;
DROP TABLE IF EXISTS FORNECEDORES;
DROP TABLE IF EXISTS ENDERECOS;
DROP TABLE IF EXISTS USUARIOS;
DROP TABLE IF EXISTS TIPO_USUARIO;


--TABELAS USUÁRIO
CREATE TABLE IF NOT EXISTS TIPO_USUARIO (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    TIPO VARCHAR(7),
    DESCRICAO VARCHAR(100),
    PRIMARY KEY(ID)
);

CREATE TABLE  IF NOT EXISTS USUARIOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NOME VARCHAR(255),
    EMAIL VARCHAR(255),
    SENHA VARCHAR(18),
    TIPO_ID BIGINT,
    PRIMARY KEY(ID)
);

ALTER TABLE USUARIOS DROP CONSTRAINT IF EXISTS FK_TIPO_USUARIO;
ALTER TABLE IF EXISTS USUARIOS ADD CONSTRAINT FK_TIPO_USUARIO FOREIGN KEY (TIPO_ID) REFERENCES TIPO_USUARIO;
------------------------------------------------------------------------------------------------------------------------
--TABELAS FORNECEDOR
CREATE TABLE IF NOT EXISTS ENDERECOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    RUA VARCHAR(255),
    NUMERO VARCHAR(10),
    BAIRRO VARCHAR(255),
    CIDADE VARCHAR(255),
    ESTADO_UF VARCHAR(2),
    CEP VARCHAR(9),
    PRIMARY KEY(ID)
);

CREATE TABLE IF NOT EXISTS FORNECEDORES (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NOME VARCHAR(255),
    CNPJ VARCHAR(20),
    CONTATO VARCHAR(60),
    ENDERECO_ID BIGINT,
    PRIMARY KEY(ID)
);

ALTER TABLE FORNECEDORES DROP CONSTRAINT IF EXISTS FK_END_FORNECEDORES;
ALTER TABLE IF EXISTS FORNECEDORES ADD CONSTRAINT FK_END_FORNECEDORES FOREIGN KEY (ENDERECO_ID) REFERENCES ENDERECOS;

------------------------------------------------------------------------------------------------------------------------
--TABELAS PRODUTO
CREATE TABLE IF NOT EXISTS PRODUTOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NOME VARCHAR(255),
    QNT_ESTOQUE INT,
    PRECO FLOAT8,
    FORNECEDOR_ID BIGINT,
    PRIMARY KEY(ID)
);

ALTER TABLE PRODUTOS DROP CONSTRAINT IF EXISTS FK_FORN_PRODUTOS;
ALTER TABLE IF EXISTS PRODUTOS ADD CONSTRAINT FK_FORN_PRODUTOS FOREIGN KEY (FORNECEDOR_ID) REFERENCES FORNECEDORES;

------------------------------------------------------------------------------------------------------------------------
--TABELAS PEDIDO
CREATE TABLE IF NOT EXISTS PEDIDOS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    DATA_PEDIDO DATE,
    STATUS_PEDIDO VARCHAR(20),
    USUARIO_ID BIGINT,
	FORNECEDOR_ID BIGINT,
	DTYPE VARCHAR(20),
    PRIMARY KEY(ID)
);

ALTER TABLE PEDIDOS DROP CONSTRAINT IF EXISTS FK_USER_PEDIDOS;
ALTER TABLE PEDIDOS DROP CONSTRAINT IF EXISTS FK_FORN_PEDIDOS;
ALTER TABLE IF EXISTS PEDIDOS ADD CONSTRAINT FK_USER_PEDIDOS FOREIGN KEY (USUARIO_ID) REFERENCES USUARIOS ON DELETE SET NULL;
ALTER TABLE IF EXISTS PEDIDOS ADD CONSTRAINT FK_FORN_PEDIDOS FOREIGN KEY (FORNECEDOR_ID) REFERENCES FORNECEDORES ON DELETE SET NULL;

CREATE TABLE IF NOT EXISTS ITEM_PEDIDO (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    QUANTIDADE INT,
    PEDIDO_ID BIGINT,
    PRODUTO_ID BIGINT,
    PRIMARY KEY(ID)
);

--ALTERAR PARA REFERENCIAR PEDIDO
ALTER TABLE ITEM_PEDIDO DROP CONSTRAINT IF EXISTS FK_PED_PRODUTOS;
ALTER TABLE ITEM_PEDIDO DROP CONSTRAINT IF EXISTS FK_PROD_ITEM;

ALTER TABLE IF EXISTS ITEM_PEDIDO ADD CONSTRAINT FK_PED_PRODUTOS FOREIGN KEY (PEDIDO_ID) REFERENCES PEDIDOS;
ALTER TABLE IF EXISTS ITEM_PEDIDO ADD CONSTRAINT FK_PROD_ITEM FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTOS ON DELETE SET NULL;
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


CREATE OR REPLACE FUNCTION verificaTipoPedido(pedido_id bigint) RETURNS integer AS $$
DECLARE tipo character varying := (SELECT dtype FROM pedidos WHERE id = pedido_id);
BEGIN
	IF (tipo = 'PedidoSaidaEstoque')
		THEN RETURN 1;
	END IF;

	RETURN 0;
END;
$$
language plpgsql;



CREATE OR REPLACE FUNCTION asseguraTemQuantidade() RETURNS trigger AS $$
BEGIN
	IF (NEW.quantidade > (SELECT qnt_estoque FROM produtos WHERE id = NEW.produto_id))
		THEN RAISE EXCEPTION 'QUANTIDADE A SER RETIRADA MAIOR DO QUE EXISTENTE EM ESTOQUE!' ;
	END IF;

	IF (NEW.quantidade <= (SELECT qnt_estoque FROM produtos WHERE id = NEW.produto_id))
		THEN UPDATE produtos SET qnt_estoque = produtos.qnt_estoque - NEW.quantidade WHERE produtos.id = NEW.produto_id;
		RETURN NEW;
	END IF;
END;
$$ language plpgsql;



CREATE OR REPLACE FUNCTION asseguraTemQuantidadeUpdate() RETURNS trigger AS $$
DECLARE
	qnt_versionada int := (SELECT quantidade FROM item_pedido WHERE id = NEW.id);
	qnt_estoque int := (SELECT qnt_estoque FROM produtos, item_pedido WHERE item_pedido.id = NEW.id AND produtos.id = item_pedido.produto_id);
	produto_id bigint := (SELECT item_pedido.produto_id FROM item_pedido WHERE item_pedido.id = NEW.id);
BEGIN

--quantidade adicionada (qnt_nova - qnt_antiga, quando qnt_nova>qnt_antiga) > quantidade em estoque: erro
	IF (NEW.quantidade > qnt_versionada AND (
			(NEW.quantidade - qnt_versionada) >qnt_estoque))
		THEN RAISE EXCEPTION 'QUANTIDADE A SER RETIRADA MAIOR DO QUE EXISTENTE EM ESTOQUE!' ;
	END IF;

--quantidade retirada (qnt_antiga - qnt_nova, quando qnt_nova<qnt_antiga) < quantidade em estoque: adicionar no estoque quantidade retirada do pedido
	IF (NEW.quantidade < qnt_versionada)
		THEN UPDATE produtos SET qnt_estoque = produtos.qnt_estoque + (qnt_versionada - NEW.quantidade) WHERE produtos.id = produto_id;
		RETURN NEW;
	END IF;

--quantidade adicionada (qnt_nova - qnt_antiga, quando qnt_nova>qnt_antiga) <= quantidade em estoque: retirar do estoque quantidade adicionada no pedido
	IF (NEW.quantidade > qnt_versionada AND (
			(NEW.quantidade - qnt_versionada) <= qnt_estoque))
		THEN UPDATE produtos SET qnt_estoque = produtos.qnt_estoque - NEW.quantidade WHERE produtos.id = produto_id;
		RETURN NEW;
	END IF;
END;
$$ language plpgsql;



CREATE OR REPLACE FUNCTION asseguraTemQuantidadeDelete() RETURNS trigger AS $$
DECLARE
	qnt_versionada int := (SELECT quantidade FROM item_pedido WHERE id = OLD.id);
	qnt_estoque int := (SELECT qnt_estoque FROM produtos, item_pedido WHERE item_pedido.id = OLD.id AND produtos.id = item_pedido.produto_id);
	produto_id bigint := (SELECT item_pedido.produto_id FROM item_pedido WHERE item_pedido.id = OLD.id);
BEGIN

--quantidade retirada (qnt_antiga - qnt_nova, quando qnt_nova<qnt_antiga) < quantidade em estoque: adicionar no estoque quantidade retirada do pedido
	UPDATE produtos SET qnt_estoque = produtos.qnt_estoque + OLD.quantidade WHERE produtos.id = produto_id;
	RETURN OLD;
END;
$$ language plpgsql;




--TRIGGER para garantir que nao crie um pedido de saida com mais produtos do que existente em estoque NA INSERÇÃO
CREATE OR REPLACE TRIGGER trg_qnt_estoque
BEFORE INSERT ON item_pedido
FOR EACH ROW
	WHEN(verificaTipoPedido(NEW.pedido_id) = 1)
		EXECUTE FUNCTION asseguraTemQuantidade();



--TRIGGER para garantir que nao crie um pedido de saida com mais produtos do que existente em estoque NA ATUALIZAÇÃO
--garante tambem que quando a quantidade eh alterada para menor do que antes, retorne ao estoque os produtos
CREATE OR REPLACE TRIGGER trg_qnt_estoque_update
BEFORE UPDATE OF quantidade ON item_pedido
FOR EACH ROW
	WHEN(verificaTipoPedido(NEW.pedido_id) = 1)
		EXECUTE FUNCTION asseguraTemQuantidadeUpdate();


--TRIGGER para garantir que nao crie um pedido de saida com mais produtos do que existente em estoque NA ATUALIZAÇÃO
--garante tambem que quando a quantidade eh alterada para menor do que antes, retorne ao estoque os produtos
CREATE OR REPLACE TRIGGER trg_qnt_estoque_delete
BEFORE DELETE ON item_pedido
FOR EACH ROW
	WHEN(verificaTipoPedido(OLD.pedido_id) = 1)
		EXECUTE FUNCTION asseguraTemQuantidadeDelete();
