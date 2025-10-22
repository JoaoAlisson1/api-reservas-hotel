create table usuarios(
    id serial not null primary key,
    login varchar(100) unique not null,
    permissao varchar(20)
);