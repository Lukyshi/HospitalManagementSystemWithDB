package util;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalDate;
import java.sql.SQLException;


public class ConvertSqlDateTimeToLocal {

    // convert sql time to local time
    public static LocalTime toLocalTIme(Time sqlTime) {
        return (sqlTime !=  null) ? sqlTime.toLocalTime() : null;
    }

    // convert sql date to local date
    public static LocalDate toLocalDate(Date sqlDate) {
        return (sqlDate != null) ? sqlDate.toLocalDate() : null;
    }

    public static LocalTime getlocalTime(ResultSet rs, String columnLabel) throws SQLException {
        Time sqlTime = rs.getTime(columnLabel);
        return toLocalTIme(sqlTime);
    }

    public static LocalDate getlocalDate(ResultSet rs, String columnLabel) throws SQLException {
        Date sqlDate = rs.getDate(columnLabel);
        return toLocalDate(sqlDate);
    }
}
