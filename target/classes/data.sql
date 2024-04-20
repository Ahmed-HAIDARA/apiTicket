DROP TABLE IF EXISTS dbusers;
DROP TABLE IF EXISTS tickets;

CREATE TABLE dbusers (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    email VARCHAR(190) UNIQUE,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role VARCHAR(250) NOT NULL
);

INSERT INTO dbusers (email, username, password, role) VALUES
    ('hello.haidara@gmail.com', 'Ahmed', 'jklg*_56', 'ADMIN'),
    ('john@gmail.com', 'johnd', 'm38rmF$', 'ADMIN'),
    ('morrison@gmail.com', 'mor_2314', '83r5^_', 'ADMIN'),
    ('kevin@gmail.com', 'kevinryan', 'kev02937@', 'ADMIN'),
    ('don@gmail.com', 'donero', 'ewedon', 'ADMIN');

CREATE TABLE tickets (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(250) NOT NULL,
    userid INT NOT NULL,
    FOREIGN KEY(userid) REFERENCES dbusers(id)
);

INSERT INTO tickets (title, description, status, userid) VALUES
    ('Newcastle upon Tyne Leazes Park', 'In The Park presents Lost Minds', 'en cours', 1),
    ('Glasgow', 'Rockstar Energy Drink presents TRNSMT- 2 Day VIP- Friday/Saturday', 'en cours', 1),
    ('Holkham', 'Gone Wild Festival Norfolk - Weekend Camping Ticket', 'terminé', 2),
    ('Norfolk', 'Creamfields 2024 - 4 day camping - Silver', 'en cours', 3),
    ('Cheshire', 'Creamfields 2024 - 3 day camping - Gold PAYMENT PLAN', 'en cours', 4);