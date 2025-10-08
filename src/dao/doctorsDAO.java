package dao;
import model.doctors;
import util.ConvertSqlDateTimeToLocal;
import util.DBConnection;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class doctorsDAO {

    public void addDoctors(doctors doctors) {
        String sql = "INSERT INTO doctors (name_doc, specialization, contact_number, schedule)" +
                "VALUES (?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, doctors.getDoctors_name());
            statement.setString(2, doctors.getSpecialization());
            statement.setString(3, doctors.getContact_number());
            statement.setDate(4, java.sql.Date.valueOf(doctors.getSchedule()));

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Failed to add doctors " + e.getMessage());
        }
    }

    public List<doctors> viewAllDoctors() {
        List<doctors> doctorsInfo = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                doctors docsInfo = mapRowForDoctors(rs);
                doctorsInfo.add(docsInfo);
            }

        } catch (SQLException e) {
            System.out.print("Viewing doctorsInfo failed " + e.getMessage());
        }
        return doctorsInfo;
    }

    public doctors mapRowForDoctors(ResultSet rs) throws SQLException {

        LocalDate schedule = ConvertSqlDateTimeToLocal.getlocalDate(rs, "schedule");
        //java.sql.Date sqlDate = rs.getDate("schedule");
        //convert sqlDate to localDate
        //LocalDate schedule = sqlDate != null ? sqlDate.toLocalDate() : null;

        return new doctors(
                rs.getInt("id"),
                rs.getString("name_doc"),
                rs.getString("specialization"),
                rs.getString("contact_number"),
                schedule

        );
    }

    public void updateDoctorsInfo(int id, String doctors_name, String specialization,
                                  String contact_number, LocalDate schedule) {
        String sql = "UPDATE doctors SET name_doc = ?, specialization = ?, contact_number = ?," +
                "schedule = ? WHERE id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctors_name);
            statement.setString(2, specialization);
            statement.setString(3, contact_number);
            statement.setDate(4, java.sql.Date.valueOf(schedule));
            statement.setInt(5, id);

            int rows = statement.executeUpdate();

            if(rows > 0) {
                System.out.print("Update Doctors Info Successfully");
            }else {
                System.out.print("can't find doctors info");
            }

        }catch (SQLException e) {
            System.out.print("Update Doctors Info Failed " + e.getMessage());
        }
    }

    public void deleteDoctorsInfo(int id) {
        String sql = "delete from doctors where id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if(rows > 0) {
                System.out.print("Delete doctors info successfully");
            }

        }catch (SQLException e) {
            System.out.print("Delete doctors info failed");
        }
    }
}
