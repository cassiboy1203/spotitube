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

create table playlists
(
    id    int auto_increment,
    name  varchar(25) not null,
    owner int         not null,
    constraint playlists_pk
        primary key (id),
    constraint owner_FK
        foreign key (owner) references users (userid)
);

create unique index playlists_id_uindex
    on playlists (id);

create table playlistTracks
(
    playlist int not null,
    track    int not null,
    constraint playlistTracks_pk
        primary key (playlist, track),
    constraint playlist_FK
        foreign key (playlist) references playlists (id),
    constraint track_FK
        foreign key (track) references tracks (id)
);

create unique index playlistTracks_playlist_track_uindex
    on playlistTracks (playlist, track);