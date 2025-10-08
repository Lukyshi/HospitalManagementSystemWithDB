package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.SplittableRandom;

public class patients {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String patients_contact_number;
    private String municipality;
    private String disease;

    private int doctors_id;
    private String doctorsName;
    private String specialization;
    private String status;
    private LocalDate appointment_date;
    private LocalTime appointment_time;

    public patients(int id, String name, String gender, int age, String patients_contact_number,
                    String municipality, String disease) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.patients_contact_number = patients_contact_number;
        this.municipality = municipality;
        this.disease = disease;
    }

    public patients(String name, String gender, int age, String patients_contact_number,
                    String municipality, String disease) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.patients_contact_number = patients_contact_number;
        this.municipality = municipality;
        this.disease = disease;
    }

    public patients(int id, String name, String gender, int age, String disease,
                    doctors doctors, appointments appointments) {
        // constructors for view appointments by patient
        this.id = id;
        this.doctors_id = doctors.getId();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.disease = disease;
        this.doctorsName = doctors.getDoctors_name();
        this.specialization = doctors.getSpecialization();
        this.appointment_date = appointments.getAppointment_date();
        this.appointment_time = appointments.getAppointment_time();
        this.status = appointments.getStatus();
    }

    public patients(int id, String name, String disease) {
        this.id = id;
        this.name = name;
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getPatients_contact_number() {
        return patients_contact_number;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getDisease() {
        return disease;
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public String getDoctorsName(){
        return doctorsName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public LocalTime getAppointment_time() {
        return appointment_time;
    }


    public String toString() {
        return getId() + "|" +
                getName() + "|" +
                getGender() + "|" +
                getAge() + "|" +
                getPatients_contact_number() + "|" +
                getMunicipality() + "|" +
                getDisease();
    }

    public String toPatientString() {
        return getId() + "|" +
                getName() + "|" +
                getGender() + "|" +
                getAge() + "|" +
                getDisease() + "|" +
                getDoctors_id() + "|" +
                getDoctorsName() + "|" +
                getSpecialization() + "|" +
                getAppointment_date() + "|" +
                getAppointment_time();
    }
}
