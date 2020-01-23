CREATE SCHEMA talkdesk AUTHORIZATION postgres;

DROP TABLE talkdesk.employee IF EXISTS;

    CREATE TABLE talkdesk.employee
(
    employee_id bigint NOT NULL,
    name char NOT NULL,
    start_date time(10) with time zone NOT NULL,
    team "char" NOT NULL,
    title "char" NOT NULL,
    CONSTRAINT PK_employee_id PRIMARY KEY (employee_id)
);
ALTER TABLE talkdesk.employee OWNER TO postgres;

