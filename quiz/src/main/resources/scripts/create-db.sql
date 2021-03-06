
DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS QUESTION CASCADE;
DROP TABLE IF EXISTS ANSWER CASCADE;
DROP TABLE IF EXISTS USER_ANSWER CASCADE;


CREATE TABLE USER (
	UID INTEGER,
	USERNAME VARCHAR(50),
	ROLE VARCHAR(10),
	PASSWORD VARCHAR (256)
);

CREATE TABLE QUESTION (
	QID INTEGER PRIMARY KEY,
	QUESTION VARCHAR(256)
);

CREATE TABLE ANSWER (
	AID INTEGER PRIMARY KEY,
	QID INTEGER,
	ANSWER VARCHAR(256),
	CORRECT CHAR(1)
);

CREATE TABLE USER_ANSWER (
	UAID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	UID INTEGER,
	QID INTEGER,
	AID INTEGER,
	ORDER_NUM INTEGER
);

