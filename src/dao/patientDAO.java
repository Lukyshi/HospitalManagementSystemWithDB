package dao;
import model.patients;
import util.DBConnection;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/*/
add patient
view patient
update patient
delete patient
 */
public class patientDAO {

    public void addPatients(patients patientsAdd) {
        String sql = "INSERT INTO patients (name, gender, age, contact_number, municipality, disease)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patientsAdd.getName());
            statement.setString(2, patientsAdd.getGender());
            statement.setInt(3, patientsAdd.getAge());
            statement.setString(4, patientsAdd.getPatients_contact_number());
            statement.setString(5, patientsAdd.getMunicipality());
            statement.setString(6, patientsAdd.getDisease());

            statement.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Failed to add " + e.getMessage());
        }
    }

    public List<patients> viewPatients() {
        String sql = "select * from patients";
        List<patients> patientsInfo = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                patients patients = mapRowForPatients(rs);
                patientsInfo.add(patients);
            }

        }catch (SQLException e) {
            System.out.println("Failed viewing patients info " + e.getMessage());
        }
        return patientsInfo;
    }

    public patients mapRowForPatients(ResultSet rs) throws SQLException {
        return new patients(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getInt("age"),
                rs.getString("contact_number"),
                rs.getString("municipality"),
                rs.getString("disease")
        );
    }

    public void updatePatientsInfo(int id, String name, String gender, int age, String contact_number,
                                   String municipality, String disease) {
        String sql = "UPDATE patients SET name = ?, gender = ?, age = ?, contact_number = ?, municipality = ?, " +
                "disease = ? where id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setInt(3, age);
            statement.setString(4, contact_number);
            statement.setString(5, municipality);
            statement.setString(6, disease);
            statement.setInt(7, id);

            int rows = statement.executeUpdate();

            if(rows > 0) {
                System.out.println("Patients update successfully");
            }else {
                System.out.println("No existing rows");
            }
        }catch (SQLException e) {
            System.out.println("Patients update failed "+ e.getMessage());
        }
    }

    public void deletePatientsRecord(int id) {
        String sql = "DELETE FROM patients where id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int row = statement.executeUpdate();

            if(row > 0) {
                System.out.print("patientInfo deleted successfully");
            }else {
                System.out.print("patientInfo can't find");
            }
        }catch (SQLException e) {
            System.out.print("deletion patientInfo failed " + e.getMessage());
        }
    }


}
