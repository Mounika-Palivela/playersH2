create table if not exists team(
    playerId int Primary Key AUTO_INCREMENT,
    playerName varchar(255),
    jerseyNumber int,
    role varchar(255)
);