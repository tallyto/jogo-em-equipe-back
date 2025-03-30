ALTER TABLE tarefas
ADD COLUMN usuario_concluidor_id UUID REFERENCES usuarios(id);