insert into users (name, email, cpf, password) values
('admin user', 'admin@example.com', '12345678900', 'hashed_password1'),
('user one', 'user1@example.com', '12345678901', 'hashed_password2'),
('user two', 'user2@example.com', '12345678902', 'hashed_password3'),
('lojista', 'lojista@example.com', '12345678903', 'hashed_password4');

insert into account (user_id, type) values
(1, 'ADMIN'),
(2, 'COMUM'),
(3, 'COMUM'),
(4, 'LOJISTA');

insert into transfer (payer_account_id, payee_account_id, amount, idempotency_id, status) values
(1, 2, 100.0000, '9f7b8e21-3c64-4c5b-80b1-fc8a5fbc6d8a', 'PROCESSADA_SUCESSO'),
(1, 3, 200.0000, 'f3b1c6e4-93b9-4d8d-a7c1-9b6c94f5d78e', 'PROCESSADA_SUCESSO');
