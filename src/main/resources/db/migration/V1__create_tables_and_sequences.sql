-- Criação da tabela "role"
CREATE TABLE role
(
    id          UUID PRIMARY KEY NOT NULL,
    description VARCHAR(255)
);

-- Criação da tabela "usuarios"
CREATE TABLE usuarios
(
    id                         UUID PRIMARY KEY NOT NULL,
    email                      VARCHAR(255) NOT NULL,
    is_account_non_expired     BOOLEAN,
    is_account_non_locked      BOOLEAN,
    is_credentials_non_expired BOOLEAN,
    is_enabled                 BOOLEAN,
    name                       VARCHAR(255) NOT NULL,
    "password"                 VARCHAR(255) NOT NULL,
    created_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by                 VARCHAR(100) NOT NULL,
    updated_by                 VARCHAR(100) NOT NULL
);

-- Criação da tabela "desafios"
CREATE TABLE desafios
(
    id          UUID PRIMARY KEY NOT NULL,
    nome        VARCHAR(255) NOT NULL,
    descricao   TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criação da tabela "usuarios_desafios"
CREATE TABLE usuarios_desafios
(
    usuario_id UUID NOT NULL,
    desafio_id UUID NOT NULL,
    CONSTRAINT pk_usuarios_desafios PRIMARY KEY (usuario_id, desafio_id),
    CONSTRAINT fk_usuario_desafio FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_desafio_usuario FOREIGN KEY (desafio_id) REFERENCES desafios(id)
);

-- Criação da tabela "historico_pontos"
CREATE TABLE historico_pontos
(
    id               UUID PRIMARY KEY NOT NULL,
    motivo           VARCHAR(255),
    variacao_pontos  INT NOT NULL,
    usuario_id       UUID NOT NULL,
    desafio_id       UUID NOT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_historico FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_desafio_historico FOREIGN KEY (desafio_id) REFERENCES desafios(id)
);

-- Criação da tabela "pontos_usuarios"
CREATE TABLE pontos_usuarios
(
    id         UUID PRIMARY KEY NOT NULL,
    usuario_id UUID NOT NULL,
    desafio_id UUID NOT NULL,
    pontos     INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_usuario_pontos FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_desafio_pontos FOREIGN KEY (desafio_id) REFERENCES desafios(id)
);

-- Criação da tabela "tarefas"
CREATE TABLE tarefas
(
    id                  UUID PRIMARY KEY NOT NULL,
    descricao           VARCHAR(255) NOT NULL,
    pontos              INT NOT NULL,
    status              VARCHAR(50),
    desafio_id          UUID NOT NULL,
    usuario_criador_id  UUID NOT NULL,
    CONSTRAINT fk_desafio_tarefa FOREIGN KEY (desafio_id) REFERENCES desafios(id),
    CONSTRAINT fk_usuario_criador FOREIGN KEY (usuario_criador_id) REFERENCES usuarios(id)
);

-- Criação da tabela "recompensas"
CREATE TABLE recompensas
(
    id            UUID PRIMARY KEY NOT NULL,
    custo_pontos  INT NOT NULL,
    nome          VARCHAR(255) NOT NULL,
    desafio_id    UUID NOT NULL,
    CONSTRAINT fk_desafio_recompensa FOREIGN KEY (desafio_id) REFERENCES desafios(id)
);

-- Criação da tabela "user_roles"
CREATE TABLE user_roles
(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES role(id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES usuarios(id)
);
