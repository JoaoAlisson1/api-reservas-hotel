create type cargo_enum as enum ('Recepcionista', 'Gerente');

create table funcionario(
    id serial not null primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    nome varchar(50) not null,
    email varchar(100) not null unique,
    telefone varchar(20) not null,
    cargo cargo_enum not null
);

create table hospede (
     id serial primary key,
    uuid uuid default gen_random_uuid(),
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20),
    cpf varchar(14) NOT NULL UNIQUE
);

CREATE TYPE tipo_quarto AS ENUM (
    'Solteiro', 'Casal', 'Duplo', 'Triplo', 'Familiar', 'Suíte'
    );

CREATE TYPE status_quarto AS ENUM (
    'Disponível', 'Ocupado', 'Reservado', 'Manutenção', 'Limpeza', 'Indisponível'
    );

CREATE TABLE quarto (
        id serial PRIMARY KEY,
        numero varchar(10) NOT NULL,
        tipo tipo_quarto NOT NULL,
        status status_quarto NOT NULL,
        diaria decimal NOT NULL
);

CREATE TYPE status_reserva AS ENUM (
    'Reservada', 'Check-in', 'Check-out', 'Cancelada'
    );

CREATE TABLE reserva (
        id serial PRIMARY KEY,
        check_in TIMESTAMP NOT NULL,
        check_out TIMESTAMP NOT NULL,
        valor_total DECIMAL(10, 2) NOT NULL,
        status status_reserva NOT NULL,
        quarto_id INT REFERENCES quarto(id),
        hospede_id INT REFERENCES hospede(id),
        funcionario_id INT REFERENCES funcionario(id)
);



