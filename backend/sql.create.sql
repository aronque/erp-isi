
    create table enderecos (
        id bigserial not null,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        estado varchar(255),
        numero varchar(255),
        rua varchar(255),
        primary key (id)
    );

    create table fornecedores (
        endereco_id bigint unique,
        id bigserial not null,
        cnpj varchar(255),
        contato varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table item_pedido (
        quantidade_item integer,
        id bigserial not null,
        pedido_id bigint,
        produto_id bigint,
        primary key (id)
    );

    create table pedidos (
        data_pedido timestamp(6),
        fornecedor_id bigint,
        id bigserial not null,
        usuario_id bigint,
        dtype varchar(31) not null,
        status_pedido varchar(255) check (status_pedido in ('CRIADO','PROCESSANDO','PROCESSADO','FINALIZADO')),
        primary key (id)
    );

    create table pedidos_itens (
        itens_id bigint not null unique,
        pedido_id bigint not null,
        primary key (itens_id, pedido_id)
    );

    create table produtos (
        preco float(53),
        quantidade_estoque integer,
        fornecedor_id bigint,
        id bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table usuarios (
        id bigserial not null,
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        tipo_usuario varchar(255) check (tipo_usuario in ('FUNCIONARIO_ESTOQUE','FUNCIONARIO_COMUM','SUPORTE_SYS')),
        primary key (id)
    );

    alter table if exists fornecedores 
       add constraint FKe3ruv4w1gmca0j4g07f5cfxak 
       foreign key (endereco_id) 
       references enderecos;

    alter table if exists item_pedido 
       add constraint FKq6cx2t1dh4ikg93nvlpumswxx 
       foreign key (pedido_id) 
       references pedidos;

    alter table if exists item_pedido 
       add constraint FKmo38jw38syue22kf17cbggmv0 
       foreign key (produto_id) 
       references produtos;

    alter table if exists pedidos 
       add constraint FK5g0es69v35nmkmpi8uewbphs2 
       foreign key (usuario_id) 
       references usuarios;

    alter table if exists pedidos 
       add constraint FKlcb551nj8r9hn8ji6p5ccj4tk 
       foreign key (fornecedor_id) 
       references fornecedores;

    alter table if exists pedidos_itens 
       add constraint FK5scc7wxi7f926dhwg2qothwdx 
       foreign key (itens_id) 
       references item_pedido;

    alter table if exists pedidos_itens 
       add constraint FK2qk9e5fq0xytuqfsdic0c216y 
       foreign key (pedido_id) 
       references pedidos;

    alter table if exists produtos 
       add constraint FKje6bg3cu81l0e4nfprc0c7wwc 
       foreign key (fornecedor_id) 
       references fornecedores;
