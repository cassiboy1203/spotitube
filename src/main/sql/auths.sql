create table auths
(
    user  int         not null,
    token varchar(36) not null,
    constraint auths_pk
        primary key (token)
);

create unique index auths_token_uindex
    on auths (token);

create unique index auths_user_uindex
    on auths (user);