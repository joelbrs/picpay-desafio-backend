SET
    timezone = 'America/Fortaleza';

CREATE TABLE tb_user (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    userType VARCHAR(7) NOT NULL CHECK (userType IN ('LOJISTA', 'COMUM'))
);

CREATE TABLE tb_transaction (
    id SERIAL PRIMARY KEY,
    payerId INTEGER NOT NULL,
    payeeId INTEGER NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (payerId) REFERENCES tb_user(id),
    FOREIGN KEY (payeeId) REFERENCES tb_user(id)
);

INSERT INTO
    tb_user (id, nome, cpf, email, senha, userType)
VALUES
    (
        DEFAULT,
        'John Doe',
        '74267469091',
        'john@doe.com',
        '12345',
        'COMUM'
    );

INSERT INTO
    tb_user (id, nome, cpf, email, senha, userType)
VALUES
    (
        DEFAULT,
        'Bobby Brown',
        '83937324097',
        'bobby@bron.com',
        '12345',
        'LOJISTA'
    );

INSERT INTO
    tb_transaction (id, payerId, payeeId, amount)
VALUES
    (DEFAULT, 1, 1, 100.0);

INSERT INTO
    tb_transaction (id, payerId, payeeId, amount)
VALUES
    (DEFAULT, 1, 2, 23.5)