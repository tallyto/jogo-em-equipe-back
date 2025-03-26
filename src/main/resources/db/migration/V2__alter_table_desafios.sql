ALTER TABLE desafios
    ADD COLUMN criado_por UUID;

-- Adicionar a chave estrangeira para o usuário criador
ALTER TABLE desafios
    ADD CONSTRAINT fk_usuario_criador FOREIGN KEY (criado_por) REFERENCES usuarios(id);
