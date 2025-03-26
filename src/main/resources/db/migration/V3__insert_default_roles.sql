-- Inserindo roles padrão
INSERT INTO role (id, description)
VALUES
    (gen_random_uuid(), 'ADMIN'),
    (gen_random_uuid(), 'USER');
