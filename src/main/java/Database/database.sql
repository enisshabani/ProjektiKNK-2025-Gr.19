CREATE DATABASE KONVIKTI;

CREATE TABLE login(
    email varchar(100),
    password varchar(50)
);

CREATE TABLE signup(
    name varchar(50),
    lastname varchar(50),
    email varchar(100),
    password varchar(50),
    university varchar (100),
    universityYear varchar (20),
    averageGrade decimal(3, 2),
    gender varchar (10)
);