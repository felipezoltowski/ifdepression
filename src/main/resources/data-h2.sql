-- USER
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (1, '123@123.123', 'General User', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (2, 'admin@123.123', 'Admin User', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');

-- ROLES
insert into roles(id, role) values (1, 'ROLE_USER');
insert into roles(id, role) values (2, 'ROLE_ADMIN');

-- USER_ROLES
insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 2);

-- DECK
insert into deck(ID, NICKNAME, CLANNAME, KINGTOWERLEVEL, ARENA, EMAIL, DECKNAME, GUIDE, STATUS, DELETION) values (1, 'King', 'Castle', 10, 8, 'antonio@gmail.com', 'Hog Beatdown', 'This deck blá blá blá...', true, null);
insert into deck(ID, NICKNAME, CLANNAME, KINGTOWERLEVEL, ARENA, EMAIL, DECKNAME, GUIDE, STATUS, DELETION) values (2, 'M4SON', 'Dying Light', 13, 8, 'm4son.light@hotmail.com', 'Meta Breaker', 'This deck blá blá blá...', false, null);
insert into deck(ID, NICKNAME, CLANNAME, KINGTOWERLEVEL, ARENA, EMAIL, DECKNAME, GUIDE, STATUS, DELETION) values (3, 'Hendrick', 'Cachaça Furacão', 10, 8, 'hendricks@hotmail.com', 'Real Miner', 'This deck blá blá blá...', true, null);
insert into deck(ID, NICKNAME, CLANNAME, KINGTOWERLEVEL, ARENA, EMAIL, DECKNAME, GUIDE, STATUS, DELETION) values (4, 'Littlejoker', 'Cerveja Furacão', 9, 8, 'littlejoker@hotmail.com', 'Hog Princie', 'This deck blá blá blá...', true, null);
