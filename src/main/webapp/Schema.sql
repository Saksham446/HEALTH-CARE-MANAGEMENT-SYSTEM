-- Create the database (Schema) 'hospital_2'
CREATE DATABASE IF NOT EXISTS hospital_2;

-- Use the 'hospital_2' database
USE hospital_2;

-- Create the 'appointment' table
CREATE TABLE IF NOT EXISTS appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    fullname VARCHAR(45),
    gender VARCHAR(45),
    age VARCHAR(45),
    appoint_date VARCHAR(45),
    email VARCHAR(45),
    phno VARCHAR(45),
    diseases VARCHAR(45)
);

-- Create the 'doctor' table
CREATE TABLE IF NOT EXISTS doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(45),
    dob VARCHAR(45),
    qualification VARCHAR(45),
    specialist VARCHAR(45),
    email VARCHAR(45),
    mobno VARCHAR(45),
    password VARCHAR(45)
);

-- Create the 'specialist' table
CREATE TABLE IF NOT EXISTS specialist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    spec_name VARCHAR(45)
);

-- Create the 'user_dtls' table
CREATE TABLE IF NOT EXISTS user_dtls (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(45),
    email VARCHAR(45),
    password VARCHAR(45)
);
