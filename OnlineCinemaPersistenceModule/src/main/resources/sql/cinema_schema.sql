CREATE TABLE USER_INFO
(
    USER_ID     NUMERIC  (20,10)  NOT NULL,
    NAME        VARCHAR (20)  NOT NULL,
    SURNAME     VARCHAR (20)  NOT NULL,
    ID_NUMBER   VARCHAR (13)  NOT NULL,
    CONSUMPTION VARCHAR (50)  NULL,
    AMOUNT      NUMERIC  (20,10) NOT NULL,
    TELEPHONE   VARCHAR (10) NULL,

    CONSTRAINT USER_INFO_PK PRIMARY KEY (USER_ID)

);
CREATE TABLE TICKET_TYPE
(
    TICKET_TYPE_ID  NUMERIC  (20,10) NOT NULL,
    TYPE            VARCHAR (30) NOT NULL,
    DISCOUNT        VARCHAR (30) NOT NULL,

    CONSTRAINT TICKET_TYPE_PK PRIMARY KEY (TICKET_TYPE_ID)
);

CREATE TABLE SCREENING_ROOM
(
    SCREENING_ROOM_ID NUMERIC  (20,10) NOT NULL,
    CAPACITY VARCHAR (100) NOT NULL,

    CONSTRAINT SCREENING_ROOM_PK PRIMARY KEY (SCREENING_ROOM_ID)
);

CREATE TABLE SCREENING_ROOM_SEAT
(
    SCREENING_ROOM_SEAT_ID NUMERIC  (20,10) NOT NULL,
    SCREENING_ROOM_ID NUMERIC  (20,10) NOT NULL,
    SEAT_NUMBER VARCHAR (150) NOT NULL,

    CONSTRAINT SCREENING_ROOM_SEAT_PK PRIMARY KEY (SCREENING_ROOM_SEAT_ID),
    CONSTRAINT SCREENING_ROOM_FK FOREIGN KEY (SCREENING_ROOM_ID)
    REFERENCES SCREENING_ROOM (SCREENING_ROOM_ID)

);

CREATE TABLE MOVIE_INFO
(
    MOVIE_INFO_ID NUMERIC  (20,10) NOT NULL,
    MOVIE_TITLE VARCHAR (50) NOT NULL,
    DIRECTOR    VARCHAR (20) NOT NULL,
    ROLES       VARCHAR (50) NULL,
    POSTER      VARCHAR (100) NOT NULL,
    SYNOPSIS    VARCHAR (300) NOT NULL,
    PRICE       NUMERIC(20,10) NOT NULL,
    TRAILER     VARCHAR (100) NOT NULL,

    CONSTRAINT MOVIE_INFO_PK PRIMARY KEY (MOVIE_INFO_ID)
);

CREATE TABLE SCREENING_INFO
(
    SCREENING_INFO_ID NUMERIC(20,10) NOT NULL,
    MOVIE_INFO_ID NUMERIC(20,10) NOT NULL,
    SCREENING_ROOM_ID NUMERIC(20,10) NOT NULL,
    SHOWTIMES      TIMESTAMP NOT NULL,
    ADDMISSION_TIME TIMESTAMP NOT NULL,

    CONSTRAINT SCREENING_INFO_PK PRIMARY KEY (SCREENING_INFO_ID),
    CONSTRAINT SCREENING_INFO_FK FOREIGN KEY (MOVIE_INFO_ID)
    REFERENCES MOVIE_INFO (MOVIE_INFO_ID),
    CONSTRAINT SCREENING_INFO_FK_1 FOREIGN KEY(SCREENING_ROOM_ID)
    REFERENCES SCREENING_ROOM (SCREENING_ROOM_ID)
);

CREATE  TABLE TICKET_INFO
(
    TICKET_ID NUMERIC(20,10) NOT NULL,
    TICKET_TYPE_ID NUMERIC(20,10) NOT NULL,
    SCREENING_INFO_ID NUMERIC(20,10) NOT NULL,
    SEAT_NUMBER VARCHAR (30) NOT NULL,
    PRICE NUMERIC(20,10) NOT NULL,

    CONSTRAINT TICKET_INFO_PK PRIMARY KEY (TICKET_ID),
    CONSTRAINT TICKET_INFO_FK FOREIGN KEY (TICKET_TYPE_ID)
    REFERENCES TICKET_TYPE (TICKET_TYPE_ID),
    CONSTRAINT TICKET_INFO_FK_1 FOREIGN KEY (SCREENING_INFO_ID)
    REFERENCES SCREENING_INFO (SCREENING_INFO_ID)


);
CREATE TABLE  BOOKING
(
    BOOKING_ID NUMERIC(20,10) NOT NULL,
    USER_ID  NUMERIC(20,10) NOT NULL,
    TICKET_TYPE_ID NUMERIC(20,10) NOT NULL,
    TICKET_ID   NUMERIC(20,10) NOT NULL,

    CONSTRAINT BOOKING_PK PRIMARY KEY (TICKET_ID),
    CONSTRAINT BOOKING_FK FOREIGN KEY (TICKET_TYPE_ID)
    REFERENCES TICKET_TYPE (TICKET_TYPE_ID),
    CONSTRAINT BOOKING_FK_1 FOREIGN KEY (TICKET_ID)
    REFERENCES TICKET_INFO (TICKET_ID),
    CONSTRAINT BOOKING_FK_2 FOREIGN KEY (USER_ID)
    REFERENCES USER_INFO (USER_ID)

);

CREATE SEQUENCE USER_INFO_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE TICKET_TYPE_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE SCREENING_ROOM_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE SCREENING_ROOM_SEAT_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE MOVIE_INFO_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE SCREENING_INFO_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE TICKET_INFO_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;
CREATE SEQUENCE BOOKING_SEQ START WITH 1 INCREMENT BY 1 CACHE 20;