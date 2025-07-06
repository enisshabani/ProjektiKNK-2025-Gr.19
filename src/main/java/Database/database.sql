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


-- Add this at the beginning of your file (before rooms table)
CREATE TABLE buildings (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           address VARCHAR(255),
                           description TEXT
);

-- Then modify your rooms table to match exactly this:
CREATE TABLE rooms (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       room_number VARCHAR(10) NOT NULL,
                       building_id INT NOT NULL,
                       floor INT NOT NULL,
                       gender ENUM('Male', 'Female', 'Mixed') NOT NULL,
                       status ENUM('Available', 'Occupied', 'Maintenance', 'Reserved') NOT NULL DEFAULT 'Available',
                       capacity INT NOT NULL CHECK (capacity > 0),
                       current_occupancy INT DEFAULT 0,
                       amenities TEXT,
                       price DECIMAL(10, 2),
                       student_id INT NULL,
                       UNIQUE (room_number, building_id),
                       FOREIGN KEY (building_id) REFERENCES buildings(id),
                       FOREIGN KEY (student_id) REFERENCES signup(id) ON DELETE SET NULL
);

INSERT INTO buildings (name, address, description) VALUES
                                                       ('Ndërtesa A', 'Rruga e Universitetit', 'Ndërtesa kryesore e konviktit'),
                                                       ('Ndërtesa B', 'Rruga e Studentëve', 'Ndërtesa e re e konviktit');


CREATE TABLE students (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL,
                          lastname VARCHAR(50) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          personal_number VARCHAR(20) UNIQUE NOT NULL,
                          gender ENUM('Male', 'Female') NOT NULL,
                          university VARCHAR(100) NOT NULL,
                          study_year INT NOT NULL,
                          room_id INT NULL, -- Lidhje me dhomen e caktuar
                          FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE SET NULL
);

CREATE TABLE room_assignments (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  room_id INT NOT NULL,
                                  student_id INT NOT NULL,
                                  assignment_date DATE NOT NULL,
                                  end_date DATE,
                                  status ENUM('Active', 'Completed', 'Terminated') NOT NULL DEFAULT 'Active',
                                  contract_document LONGBLOB,
                                  FOREIGN KEY (room_id) REFERENCES rooms(id),
                                  FOREIGN KEY (student_id) REFERENCES signup(id),
                                  UNIQUE (room_id, student_id) -- Prevent duplicate assignments
);

CREATE TABLE maintenance_requests (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      room_id INT NOT NULL,
                                      reporter_id INT NOT NULL,
                                      request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      issue_type VARCHAR(50) NOT NULL,
                                      description TEXT NOT NULL,
                                      priority ENUM('Low', 'Medium', 'High', 'Emergency') NOT NULL,
                                      status ENUM('Pending', 'In Progress', 'Completed', 'Cancelled') NOT NULL DEFAULT 'Pending',
                                      completion_date TIMESTAMP NULL,
                                      FOREIGN KEY (room_id) REFERENCES rooms(id),
                                      FOREIGN KEY (reporter_id) REFERENCES signup(id)
);
CREATE INDEX idx_rooms_building ON rooms(building_id);
CREATE INDEX idx_rooms_status ON rooms(status);
CREATE INDEX idx_rooms_gender ON rooms(gender);
CREATE INDEX idx_applications_status ON applications(status);
CREATE INDEX idx_assignments_status ON room_assignments(status);

INSERT INTO rooms (room_number, gender, status, building, capacity)
VALUES
    ('101', 'Male', 'Available', 'Ndërtesa A', 2),
    ('102', 'Female', 'Available', 'Ndërtesa A', 2),
    ('201', 'Mixed', 'Available', 'Ndërtesa B', 3);

INSERT INTO students (name, lastname, email, personal_number, gender, university, study_year)
VALUES
    ('John', 'Doe', 'john@university.edu', '12345', 'Male', 'Universiteti i Tiranës', 1),
    ('Jane', 'Smith', 'jane@university.edu', '67890', 'Female', 'Universiteti i Tiranës', 2);

CREATE TABLE applications (
                              application_date DATE,
                              student_id INT,
                              status VARCHAR(20),
                              deadline DATE,
                              FOREIGN KEY (student_id) REFERENCES students(id)
);