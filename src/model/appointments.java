package model;
import java.time.LocalDate;
import java.time.LocalTime;
import model.*;

public class appointments {
    private int id;
    private int patients_id;
    private int doctors_id;
    private LocalDate appointment_date;
    private LocalTime appointment_time;
    private String status;

    public appointments(int id, patients patients, doctors doctors, LocalDate appointment_date,
                        LocalTime appointment_time, String status) {
        this.id = id;
        this.patients_id = patients.getId();
        this.doctors_id = doctors.getId();
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public appointments(int id, int patients_id, int doctors_id, LocalDate appointment_date,
                        LocalTime appointment_time, String status) {
        this.id = id;
        this.patients_id = patients_id;
        this.doctors_id = doctors_id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public appointments(int patients_id, int doctors_id, LocalDate appointment_date,
                        LocalTime appointment_time, String status) {

        this.patients_id = patients_id;
        this.doctors_id = doctors_id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public appointments(LocalDate appointment_date, LocalTime appointment_time, String status) {
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public appointments(int id, LocalDate appointment_date, LocalTime appointment_time, String status) {
        this.id = id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPatients_id() {
        return patients_id;
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public LocalDate getAppointment_date () {
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
                getPatients_id() + "|" +
                getDoctors_id() + "|" +
                getAppointment_date() + "|" +
                getAppointment_time() + "|" +
                getStatus();
    }
}
