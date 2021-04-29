drop table if exists transaction;
drop table if exists owned_stock;
drop table if exists user;

create table user
(
    username varchar(255) not null primary key,
    balance  double,
    password varchar(255)
);

create table transaction
(
    id                bigint not null primary key auto_increment,
    amount            int,
    date              varchar(255),
    stock_price       double,
    transaction_type  varchar(255),
    transaction_value double,
    type              varchar(255),
    user_name         varchar(255),
    profit            double
);

create table owned_stock
(
    id           bigint not null primary key auto_increment,
    amount       int,
    buy_in_price double,
    type         varchar(255),
    user_name    varchar(255)
);