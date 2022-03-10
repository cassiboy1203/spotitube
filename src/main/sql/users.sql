create table users
(
    userid   int auto_increment,
    username varchar(25) not null,
    password binary(60)  not null,
    constraint users_pk
        primary key (userid)
);

create unique index users_userid_uindex
    on users (userid);

create unique index users_username_uindex
    on users (username);