CREATE DATABASE twitterdb;

CREATE USER 'twitterUser'@'localhost' IDENTIFIED BY 'jakieshaslodotwittera';
GRANT ALL PRIVILEGES ON twitterdb. * TO 'twitterUser'@'localhost';

USE twitterdb;

CREATE TABLE USER (
    ID INT NOT NULL, 
    NAME TEXT NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE TWEET (
    ID LONG NOT NULL, 
    TWEET_TEXT TEXT NOT NULL,
    CREATE_DATE DATE NOT NULL,
    USER_ID INT,
    INDEX par_ind (USER_ID),
  	FOREIGN KEY (USER_ID) 
    	REFERENCES USER(ID)
        	ON DELETE CASCADE
);