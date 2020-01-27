CREATE SCHEMA talkdesk AUTHORIZATION postgres;

DROP TABLE talkdesk.employee IF EXISTS;

    CREATE TABLE talkdesk.employee
(
    employeeId bigint NOT NULL,
    name char NOT NULL,
    startDate time(10) with time zone NOT NULL,
    team "char" NOT NULL,
    title "char" NOT NULL,
    CONSTRAINT PK_employeeId PRIMARY KEY (employeeId)
);
ALTER TABLE talkdesk.employee OWNER TO postgres;

