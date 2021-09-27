USE tournaments_db;

create table tournaments_db.usersRoles(
role_id int not null auto_increment,
role_name varchar(20),
primary key (role_id)
);

create table tournaments_db.users(
user_id int not null auto_increment,
role_id int not null,
name varchar(30),
user_name varchar(30),
password varchar(50),
is_admin boolean,
primary key (user_id),
foreign key (role_id) references tournaments_db.usersRoles(role_id)
);

create table tournaments_db.tournaments(
tournament_id int not null auto_increment,
user_id int not null,
tournament_name varchar(30),
venue varchar(30),
start_date date,
end_date date,
primary key (tournament_id),
foreign key (user_id) references tournaments_db.users(user_id)
);

create table tournaments_db.players(
player_id int not null auto_increment,
player_name varchar(20),
user_id int not null,
primary key(player_id),
foreign key (user_id) references tournaments_db.users(user_id)
);

create table tournaments_db.teams(
team_id int not null auto_increment,
user_id int not null,
team_name varchar(25),
primary key(team_id),
foreign key (user_id) references tournaments_db.users(user_id)
);

create table team_players(
tp_id int not null auto_increment,
team_id int not null,
player_id int not null,
primary key(tp_id),
foreign key (team_id) references tournaments_db.teams(team_id)
foreign key (player_id) references tournaments_db.players(player_id)
);