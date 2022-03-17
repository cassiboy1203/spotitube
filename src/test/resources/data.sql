INSERT INTO auths (user, token) VALUES (1, '1234-1234-1234');
INSERT INTO auths (user, token) VALUES (2, '2345-2345-2345');
INSERT INTO auths (user, token) VALUES (3, '3456-3456-3456');
INSERT INTO auths (user, token) VALUES (4, '4567-4567-4567');

INSERT INTO playlists (id, name, owner) VALUES (1, 'playlist1', 1);
INSERT INTO playlists (id, name, owner) VALUES (2, 'playlist2', 1);
INSERT INTO playlists (id, name, owner) VALUES (3, 'playlist3', 4);
INSERT INTO playlists (id, name, owner) VALUES (4, 'playlist4', 8);

INSERT INTO playlistTracks (playlist, track) VALUES (1,1);
INSERT INTO playlistTracks (playlist, track) VALUES (1,2);
INSERT INTO playlistTracks (playlist, track) VALUES (1,3);
INSERT INTO playlistTracks (playlist, track) VALUES (4,1);

INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES (1, 'title1', 'performer1', 500, 'album1', 0, 1647346526, 'description1', false);
INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES (2, 'title2', 'performer2', 600, 'album2', 1, 1647346644, 'description2', true);
INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES (3, 'title3', 'performer3', 856, 'album3', 10, 1647346650, 'description3', true);
INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES (4, 'title4', 'performer4', 100, 'album4', 19, 1647346656, 'description4', true);
INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES (5, 'title5', 'performer5', 3600, 'album5', 20, 1647346661, 'description5', false);
INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES (6, 'title6', 'performer6', 2100, 'album6', 11, 1647346668, 'description6', false);

INSERT INTO users (userid, username, password) VALUES (1, 'bob', 'bob');
INSERT INTO users (userid, username, password) VALUES (2, 'bob2', 'bob2');
INSERT INTO users (userid, username, password) VALUES (3, 'bob3', 'bob3');
INSERT INTO users (userid, username, password) VALUES (4, 'bob4', 'qwerty');
INSERT INTO users (userid, username, password) VALUES (5, 'bob5', 'abcd');