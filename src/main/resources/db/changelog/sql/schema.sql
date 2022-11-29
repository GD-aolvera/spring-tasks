CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE event_status AS ENUM('SCHEDULED', 'DONE', 'CANCELLED');

CREATE CAST (varchar AS event_status) WITH INOUT AS IMPLICIT;

CREATE TYPE patient_status AS ENUM('IN_TREATMENT', 'RECOVERED', 'DISCHARGED');

CREATE CAST (varchar AS patient_status) WITH INOUT AS IMPLICIT;

CREATE TYPE prescription_status AS ENUM('ACTIVE', 'INACTIVE');

CREATE CAST (varchar AS prescription_status) WITH INOUT AS IMPLICIT;

CREATE TYPE treatment_type AS ENUM('PROCEDURE', 'MEDICINE');

CREATE CAST (varchar AS treatment_type) WITH INOUT AS IMPLICIT;

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
     period          INT4,
     time_pattern    TEXT,
     treatment_id    uuid DEFAULT uuid_generate_v4 (),
     status          prescription_status,
     PRIMARY KEY (id),
     FOREIGN KEY (treatment_id)
        REFERENCES treatments(id),
     FOREIGN KEY (patient_id)
        REFERENCES patients(id)
  );


