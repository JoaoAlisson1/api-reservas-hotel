
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE funcionario(

    id serial not null primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    nome varchar(50) not null,
    email varchar(100) not null unique,
    telefone varchar(20) not null,
    cargo varchar(100)
);

CREATE TABLE hospede (

    id serial primary key,
    uuid uuid default gen_random_uuid(),
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20),
    cpf varchar(14) NOT NULL UNIQUE
);


CREATE TABLE quarto (
    id serial PRIMARY KEY,
    numero varchar(10) NOT NULL,
    tipo varchar(50) NOT NULL,
    status varchar(50) NOT NULL,
    diaria decimal NOT NULL
);


CREATE TABLE reserva (
    id serial PRIMARY KEY,
    check_in TIMESTAMP NOT NULL,
    check_out TIMESTAMP NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    status varchar(30) NOT NULL,
    quarto_id INT REFERENCES quarto(id),
    funcionario_id INT REFERENCES funcionario(id)
);

/* Tabela associativa para N:N reserva <-> hospede */
CREATE TABLE reserva_hospede (

    reserva_id INT NOT NULL REFERENCES reserva(id) ON DELETE CASCADE,
    hospede_id INT NOT NULL REFERENCES hospede(id) ON DELETE CASCADE,
    PRIMARY KEY (reserva_id, hospede_id)
);
