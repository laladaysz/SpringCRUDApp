CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username varchar(200),
    password varchar(200),
    first_name varchar(30),
    last_name varchar(30),
    age int,
    occupation varchar(255),
    role varchar(50)
)