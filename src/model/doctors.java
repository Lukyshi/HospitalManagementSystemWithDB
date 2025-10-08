package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class doctors {
    private int id;
    private String doctors_name;
    private String specialization;
    private String contact_number;
    private LocalDate schedule;

    private int patients_id;
    private String patients_name;
    private String patientsDisease;

    private LocalDate appointment_date;
    private LocalTime appointment_time;
    private String status;

    public doctors(int id, String doctors_name, String specialization, String contact_number,
                   LocalDate schedule){
        this.id = id;
        this.doctors_name = doctors_name;
        this.specialization = specialization;
        this.contact_number = contact_number;
        this.schedule = schedule;
    }

    public doctors(String doctors_name, String specialization, String contact_number,
                   LocalDate schedule){
        this.doctors_name = doctors_name;
        this.specialization = specialization;
        this.contact_number = contact_number;
        this.schedule = schedule;
    }

    public doctors(int id, String doctors_name, String specialization, patients patients,
                   appointments appointments) {
        this.id = id;
        this.doctors_name = doctors_name;
        this.specialization = specialization;
        this.patients_id = patients.getId();
        this.patients_name = patients.getName();
        this.patientsDisease = patients.getDisease();
        this.appointment_date = appointments.getAppointment_date();
        this.appointment_time = appointments.getAppointment_time();
        this.status = appointments.getStatus();

    }

    public doctors(int id, String doctors_name, String specialization) {
        this.id = id;
        this.doctors_name = doctors_name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public int getPatients_id() {
        return patients_id;
    }

    public String getDoctors_name() {
        return doctors_name;
    }

    public String getPatients_name() {
        return patients_name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getContact_number() {
        return contact_number;
    }

    public LocalDate getSchedule() {
        return schedule;
    }

    public String getPatientsDisease() {
        return patientsDisease;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public LocalTime getAppointment_time() {
        return appointment_time;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return getId() + "|" +
                getDoctors_name() + "|" +
                getSpecialization() + "|" +
                getContact_number() + "|" +
                getSchedule();
    }

    public String toDoctorsString() {
        return getId() + "|" +
                getDoctors_name() + "|" +
                getSpecialization() + "|" +
                getPatients_id() + "|" +
                getPatients_name() + "|" +
                getPatientsDisease() + "|" +
                getAppointment_date() + "|" +
                getAppointment_time() + "|" +
                getStatus();
    }


}
