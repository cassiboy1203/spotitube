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