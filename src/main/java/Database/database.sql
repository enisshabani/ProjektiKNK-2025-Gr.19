CREATE DATABASE KONVIKTI;

CREATE TABLE login(
                      email varchar(100),
                      password varchar(255),
                      user_type varchar(20)
);

CREATE TABLE signup(
                       name varchar(50),
                       lastname varchar(50),
                       email varchar(100),
                       password varchar(255),
                       university varchar (100),
                       universityYear varchar (20),
                       averageGrade decimal(3, 2),
                       gender varchar (10)
);

CREATE TABLE forgotPassword(
                               email varchar(100),
                               confirmationCode int,
                               newPassword varchar(255)
);

CREATE TABLE applications (
                              application_date DATE,
                              student_id INT,
                              status VARCHAR(20),
                              deadline DATE,
                              FOREIGN KEY (student_id) REFERENCES students(id)
);