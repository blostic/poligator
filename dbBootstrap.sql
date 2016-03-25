CREATE DATABASE twitterdb;

CREATE USER 'twitterUser'@'localhost' IDENTIFIED BY 'jakieshaslodotwittera';
GRANT ALL PRIVILEGES ON twitterdb. * TO 'twitterUser'@'localhost';

USE twitterdb;

CREATE TABLE TWEET(
    id LONG NOT NULL, 
    text TEXT
);