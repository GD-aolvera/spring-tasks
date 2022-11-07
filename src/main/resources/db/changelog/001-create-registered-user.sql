set search_path = public;

CREATE TABLE patient_table
(
    insurance_number        INT             PRIMARY KEY (id_patient),
    first_name              VARCHAR         NOT NULL,
    last_name               VARCHAR         NOT NULL,
    diagnosis               VARCHAR         NOT NULL,
    doctor                  VARCHAR         NOT NULL,
    status                  VARCHAR         NOT NULL
);

CREATE TABLE prescription_table
(
    id                      VARCHAR         PRIMARY KEY (id),
    patient                 VARCHAR         NOT NULL,
    type                    VARCHAR         NOT NULL,
    time_pattern            VARCHAR         NOT NULL,
    period                  VARCHAR         NOT NULL
);

CREATE TABLE event_table
(
    id                      VARCHAR         PRIMARY KEY (id),
    patient                 VARCHAR         NOT NULL,
    date_and_time           DATE            NOT NULL,
    status                  VARCHAR         NOT NULL,
    type                    VARCHAR         NOT NULL
);

CREATE TABLE procedure_medicine_table
(
    id                      INT             PRIMARY KEY (id),
    name                    VARCHAR         NOT NULL,
    type                    VARCHAR         NOT NULL
);