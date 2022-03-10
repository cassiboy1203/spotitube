create table tracks
(
    id               int auto_increment,
    title            varchar(50)    not null,
    performer        varchar(25)    not null,
    duration         int            not null,
    album            varchar(50)    not null,
    playcount        int            null,
    publicationDate  int            null,
    description      varchar(10000) null,
    offlineAvailable bit default 0  not null,
    constraint tracks_pk
        primary key (id)
);

create unique index tracks_id_uindex
    on tracks (id);