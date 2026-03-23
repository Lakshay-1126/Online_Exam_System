create database oes;
use oes;

CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(50)
);

CREATE TABLE question (
    q_id INT PRIMARY KEY AUTO_INCREMENT,
    question TEXT,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    correct_option INT
);

CREATE TABLE result (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    score INT,
    total INT,
    exam_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO admin(username,password)
VALUES('lakshay','1234');
