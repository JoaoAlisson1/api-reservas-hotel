-- Ativa a extensão pgcrypto para geração de UUIDs, se ainda não estiver ativa
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Adiciona a coluna UUID à tabela reserva
ALTER TABLE reserva
    ADD COLUMN uuid UUID DEFAULT gen_random_uuid();

-- Garante que o valor da coluna UUID seja único
ALTER TABLE reserva
    ADD CONSTRAINT reserva_uuid_unique UNIQUE (uuid);
