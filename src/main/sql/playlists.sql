create table playlists
(
    id    int auto_increment,
    name  varchar(25) not null,
    owner int         null,
    constraint playlists_pk
        primary key (id),
    constraint owner_FK
        foreign key (owner) references users (userid)
);

create unique index playlists_id_uindex
    on playlists (id);