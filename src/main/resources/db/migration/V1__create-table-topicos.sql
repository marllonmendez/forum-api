create table topicos(

    id bigserial not null,
    titulo varchar(255) not null,
    mensagem varchar(255) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,
    ativo boolean not null,
    data_criacao timestamp not null,

    primary key (id)

);