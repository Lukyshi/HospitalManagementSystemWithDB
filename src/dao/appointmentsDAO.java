package dao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import model.*;
import util.ConvertSqlDateTimeToLocal;
import util.DBConnection;
import java.time.LocalDate;
import java.time.LocalTime;

public class appointmentsDAO {

    public void bookAppointment(appointments appointments) {
        String sql = "INSERT INTO appointments (patients_id, doctors_id, appointment_date," +
                "appointment_time, status) VALUES(?, ?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointments.getPatients_id());
            statement.setInt(2, appointments.getDoctors_id());
            statement.setDate(3, java.sql.Date.valueOf(appointments.getAppointment_date()));
            statement.setTime(4, java.sql.Time.valueOf(appointments.getAppointment_time()));
            statement.setString(5, appointments.getStatus());

            statement.executeUpdate();

        }catch (SQLException e) {
            System.out.print("failed to add appointments " + e.getMessage());
        }
    }

    public List<appointments> viewAppointments() {
        List<appointments> appointmentsAvail = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                appointments appointmentIn = mapRowForAppointments(rs);
                appointmentsAvail.add(appointmentIn);
            }

        }catch (SQLException e) {
            System.out.print("view all Appointments failed " + e.getMessage());
        }
        return appointmentsAvail;
    }

    public appointments mapRowForAppointments(ResultSet rs) throws SQLException {

        //before
        //java.sql.Date sqlDate = rs.getDate("appointment_date");
        //LocalDate appointmentDate = sqlDate != null ? sqlDate.toLocalDate() : null;

        //after
        LocalDate appointmentDate = ConvertSqlDateTimeToLocal.getlocalDate(rs, "appointment_date");

        //before
        //java.sql.Time sqlTime = rs.getTime("appointment_time");
        //LocalTime appointmentTime = sqlTime != null ? sqlTime.toLocalTime() : null;

        //after
        LocalTime appointmentTime = ConvertSqlDateTimeToLocal.getlocalTime(rs, "appointment_time");

        return new appointments(
                rs.getInt("id"),
                rs.getInt("patients_id"),
                rs.getInt("doctors_id"),
                appointmentDate,
                appointmentTime,
                rs.getString("status")
        );
    }

    public patients viewAppointmentByPatients(int id) {
        String sql = "SELECT " +
                "p.id AS patients_id, " +
                "p.name AS patients_name, " +
                "p.gender, " +
                "p.age, " +
                "p.disease, " +
                "d.id AS doctors_id, " +
                "d.name_doc AS doctors_name, " +
                "d.specialization, " +
                "a.appointment_date, " +
                "a.appointment_time, " +
                "a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patients_id = p.id " +
                "JOIN doctors d ON a.doctors_id = d.id " +
                "WHERE p.id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                LocalDate appointmentDate = ConvertSqlDateTimeToLocal.getlocalDate(rs, "appointment_date");
                //java.sql.Date sqlDate = rs.getDate("appointment_date");
                //LocalDate appointmentDate = sqlDate != null ? sqlDate.toLocalDate() : null;

                LocalTime appointmentTime = ConvertSqlDateTimeToLocal.getlocalTime(rs, "appointment_time");
                //java.sql.Time sqlTime = rs.getTime("appointment_time");
                //LocalTime appointmentTime = sqlTime != null ? sqlTime.toLocalTime() : null;

                doctors doctors = new doctors(
                        rs.getInt("doctors_id"),
                        rs.getString("doctors_name"),
                        rs.getString("specialization")
                );

                appointments appointments = new appointments(
                        appointmentDate,
                        appointmentTime,
                        rs.getString("status")
                );

                return new patients(
                        rs.getInt("patients_id"),
                        rs.getString("patients_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("disease"),
                        doctors,
                        appointments
                );

            }
        }catch (SQLException e) {
            System.out.print("failed to view patients appointments" + e.getMessage());
        }
        return null;
    }

    public doctors viewAppointmentsByDoctors(int id) {
        String sql = "SELECT \n" +
                "d.id AS doctors_id, " +
                "d.name_doc AS doctors_name, " +
                "d.specialization, " +
                "p.id AS patients_id, " +
                "p.name AS patients_name, " +
                "p.disease, " +
                "a.appointment_date, " +
                "a.appointment_time, " +
                "a.status " +
                "FROM appointments a " +
                "JOIN doctors d ON a.doctors_id = d.id " +
                "JOIN patients p ON a.patients_id = p.id " +
                "WHERE d.id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(rs.next()) {

                LocalDate appointmentDate = ConvertSqlDateTimeToLocal.getlocalDate(rs, "appointment_date");
                //java.sql.Date sqlDate = rs.getDate("appointment_date");
                //LocalDate appointmentDate = sqlDate != null ? sqlDate.toLocalDate() : null;

                LocalTime appointmentTime = ConvertSqlDateTimeToLocal.getlocalTime(rs, "appointment_time");
                //java.sql.Time sqlTime = rs.getTime("appointment_time");
                //LocalTime appointmentTime = sqlTime != null ? sqlTime.toLocalTime() : null;

                patients patients = new patients(
                        rs.getInt("patients_id"),
                        rs.getString("patients_name"),
                        rs.getString("disease")
                );

                appointments appointments = new appointments(
                        appointmentDate,
                        appointmentTime,
                        rs.getString("status")
                );

                return new doctors(
                        rs.getInt("doctors_id"),
                        rs.getString("doctors_name"),
                        rs.getString("specialization"),
                        patients,
                        appointments
                );
            }

        }catch (SQLException  e) {
            System.out.print("Failed to view appointments by doctors "+ e.getMessage());
        }
        return null;
    }

    public void updateAppointment(int id, LocalDate appointment_date, LocalTime appointment_time,
                                  String status) {
        String sql = "UPDATE appointments SET appointment_date = ?, appointment_time = ?, status = ?" +
                "WHERE id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, java.sql.Date.valueOf(appointment_date));
            statement.setTime(2, java.sql.Time.valueOf(appointment_time));
            statement.setString(3, status);

            statement.setInt(4, id);

            int row = statement.executeUpdate();

            if(row > 0) {
                System.out.print("Update appointment successfully");
            }else {
                System.out.print("No appointment available");
            }

        }catch (SQLException e) {
            System.out.print("Updating appointments failed " + e.getMessage());
        }
    }

    public void cancelAppointment(int id) {
        String sql = "DELETE from appointments WHERE id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int row = statement.executeUpdate();

            if(row > 0) {
                System.out.print("Cancelling Appointment Successfully");
            }else{
                System.out.print("No Table Found");
            }

        }catch (SQLException e) {
            System.out.print("Cancel Appointment Failed" + e.getMessage());
        }
    }
}
