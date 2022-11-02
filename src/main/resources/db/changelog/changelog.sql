-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE patient_table (name VARCHAR, diagnosis VARCHAR, insurance_number VARCHAR, doctor VARCHAR, status VARCHAR)

-- changeset liquibase:2
CREATE TABLE appointment_table (patient VARCHAR, type VARCHAR, time_pattern VARCHAR, period VARCHAR)

-- changeset liquibase:3
CREATE TABLE event_table (patient VARCHAR, date_and_time VARCHAR, status VARCHAR, type VARCHAR)

-- changeset liquibase:4
CREATE TABLE procedure_medicine_table (name VARCHAR, type VARCHAR)
