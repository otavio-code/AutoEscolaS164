CREATE TABLE instrutores
(
    id            BIGINT AUTO_INCREMENT,
    nome          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    telefone      VARCHAR(20)  NOT NULL,
    cnh           VARCHAR(11)  NOT NULL UNIQUE,
    especialidade VARCHAR(10)  NOT NULL,
    logradouro    VARCHAR(100) NOT NULL,
    numero        VARCHAR(20),
    complemento   VARCHAR(100),
    bairro        VARCHAR(100) NOT NULL,
    cidade        VARCHAR(100) NOT NULL,
    uf            VARCHAR(2)   NOT NULL,
    cep           VARCHAR(9)   NOT NULL,

    PRIMARY KEY (id)
);