create type account_type as enum ('LOJISTA', 'COMUM', 'ADMIN');
create type transfer_status as enum ('EM_PROCESSAMENTO', 'PROCESSADA_SUCESSO', 'ERRO_PROCESSAMENTO');

create table users (
    id serial primary key,
    name varchar(200) not null,
    email varchar(200) unique not null,
    cpf varchar(11) unique not null,
    password varchar(200) not null
);

create table account (
    id serial primary key,
    user_id int not null,
    type account_type not null default 'COMUM',

    constraint fk_user_id foreign key (user_id) references users(id)
);

create table transfer (
    id serial primary key,
    payer_account_id int not null,
    payee_account_id int not null,
    amount decimal(10,4) not null,
    idempotency_id uuid not null,
    status transfer_status not null default 'EM_PROCESSAMENTO',

    constraint fk_payer_account_id foreign key (payer_account_id) references account(id),
    constraint fk_payee_account_id foreign key (payee_account_id) references account(id)
);
