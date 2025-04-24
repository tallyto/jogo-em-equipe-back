ALTER TABLE recompensas
add COLUMN status VARCHAR(50),
    add COLUMN resgatada BOOLEAN DEFAULT false;