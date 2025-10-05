

-- Adiciona a coluna temporária numero_int do tipo INT
ALTER TABLE quarto ADD COLUMN numero_int INT;


UPDATE quarto SET numero_int = CAST(numero AS INTEGER);

--  Remove a coluna antiga numero (varchar)
ALTER TABLE quarto DROP COLUMN numero;

--. Renomeia a coluna numero_int para numero
ALTER TABLE quarto RENAME COLUMN numero_int TO numero;
