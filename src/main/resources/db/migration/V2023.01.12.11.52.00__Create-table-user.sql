CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nickname VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    createdAt DATETIME NOT NULL,
    active TINYINT DEFAULT 1,
    role VARCHAR(50),
    primary key (id)
)