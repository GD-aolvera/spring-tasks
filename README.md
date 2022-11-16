# spring-tasks

Description of program:
An application for a medical clinic that allows management of patients.

Dockerfile was included to allow program to be run locally. Once program is running, 
to display API from swagger,  use  the following url: http://localhost:8080/swagger-ui/index.html

To access current H2 databse, it can be acces from url: http://localhost:8080/h2-console

There are the following entities:
1) Patient
- name
- birth date
- diagnosis
- medical insurance number
- doctor
- status (on treatment / recovered)
2) Appointment
- Patient
- type (procedure / medicine)
- time pattern (twice a day for example)
- Period
  Example of a procedure appointment: physiotherapy twice a week on Monday and Thursday
  Example of a medicine: take aspirin twice a day
3) Event
- patient
- date and time
- status (planned, done, cancelled)
- type (procedure / medicine)
4) Procedure / medicine
- name
- type
  Application should have the following features:
1) For doctors:
- add patient
- discharging of the patient (means that all further appointments should be cancelled)
- prescribing procedures and medications
- editing of appointments
- appointments cancellation
  When appointment is created, all events should be generated at this time.
  For example, if you want to create “physiotherapy twice a week on Monday and Thursday” for 2 weeks, 4 events should be generated
  When appointment is cancelled, all generated events should be cancelled as well
2) For nurses:
- ability to see all the events
- filtering appointments by date (for today, this week and so on)
- changing status of patient’s event from “planned” to “done” and from planned to “cancelled”
  If the nurse changes appoint to “cancelled” status, she should set up the reason of this
  11:55

There will be a user with “admin” role created from the beginning. it will have opportunity to add users with “doctor” and “nurse” roles and assign login / password to them
