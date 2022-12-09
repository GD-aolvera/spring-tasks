CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE event_status AS ENUM('SCHEDULED', 'DONE', 'CANCELLED');

CREATE CAST (varchar AS event_status) WITH INOUT AS IMPLICIT;

CREATE TYPE patient_status AS ENUM('IN_TREATMENT', 'RECOVERED', 'DISCHARGED');

CREATE CAST (varchar AS patient_status) WITH INOUT AS IMPLICIT;

CREATE TYPE prescription_status AS ENUM('ACTIVE', 'INACTIVE');

CREATE CAST (varchar AS prescription_status) WITH INOUT AS IMPLICIT;

CREATE TYPE treatment_type AS ENUM('PROCEDURE', 'MEDICINE');

CREATE CAST (varchar AS treatment_type) WITH INOUT AS IMPLICIT;

CREATE TYPE frequency_unit AS ENUM('DAY', 'WEEK','MONTH');

CREATE CAST (varchar AS frequency_unit) WITH INOUT AS IMPLICIT;

CREATE TYPE days_of_the_week AS ENUM('MONDAY', 'TUESDAY','WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY');

CREATE CAST (varchar AS days_of_the_week) WITH INOUT AS IMPLICIT;

CREATE TYPE role AS ENUM('ROLE_ADMIN', 'ROLE_NURSE','ROLE_DOCTOR');

CREATE CAST (varchar AS role) WITH INOUT AS IMPLICIT;

CREATE TABLE events
  (
     id            uuid DEFAULT uuid_generate_v4 () NOT NULL,
     prescription_id uuid DEFAULT uuid_generate_v4 () NOT NULL,
     cancel_reason TEXT,
     date_time     TIMESTAMP,
     status        event_status,
     PRIMARY KEY (id)
  );

CREATE TABLE patients
  (
     id               uuid DEFAULT uuid_generate_v4 () NOT NULL,
     created_at       TIMESTAMP,
     created_by       TEXT,
     diagnosis        TEXT,
     doctor_id        uuid DEFAULT uuid_generate_v4 (),
     insurance_number TEXT,
     first_name       TEXT,
     last_name        TEXT,
     birth_date       TIMESTAMP,
     status           patient_status,
     PRIMARY KEY (id)
  );

CREATE TABLE treatments
  (
     id   uuid DEFAULT uuid_generate_v4 () NOT NULL,
     NAME TEXT,
     type treatment_type,
     PRIMARY KEY (id)
  );

CREATE TABLE prescriptions
  (
     id              uuid DEFAULT uuid_generate_v4 () NOT NULL,
     patient_id      uuid DEFAULT uuid_generate_v4 () NOT NULL,
     date_prescribed TIMESTAMP,
     frequency          INT4,
     frequency_unit    frequency_unit,
     days_of_the_week  days_of_the_week[],
     description     TEXT,
     treatment_id    uuid DEFAULT uuid_generate_v4 (),
     status          prescription_status,
     PRIMARY KEY (id),
     FOREIGN KEY (treatment_id)
        REFERENCES treatments(id),
     FOREIGN KEY (patient_id)
        REFERENCES patients(id)
  );

CREATE TABLE Users
  (
     id              uuid DEFAULT uuid_generate_v4 () NOT NULL,
     first_name      TEXT,
     last_name      TEXT,
     username      TEXT,
     password      TEXT,
     role          role,
     created_at          TIMESTAMP,
     created_by      TEXT,
     PRIMARY KEY (id)
  );

CREATE TABLE refresh_token
  (
     id              uuid DEFAULT uuid_generate_v4 () NOT NULL,
     user_id         uuid DEFAULT uuid_generate_v4 () NOT NULL,
     token      TEXT,
     expiration TIMESTAMP,
     PRIMARY KEY (id),
     FOREIGN KEY (user_id)
        REFERENCES Users(id)
  );

CREATE TABLE prescription_days_of_the_week
  (
     id              uuid DEFAULT uuid_generate_v4 () NOT NULL,
     prescription_id         uuid DEFAULT uuid_generate_v4 () NOT NULL,
     days_of_the_week          days_of_the_week,
     PRIMARY KEY (id),
     FOREIGN KEY (prescription_id)
        REFERENCES prescriptions(id)
  );


