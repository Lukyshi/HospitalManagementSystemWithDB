package util;
import java.time.DateTimeException;
import java.util.*;
import java.time.LocalTime;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;

import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class ExceptionHandling {
    private static final Scanner scan = new Scanner(System.in);

    public static LocalTime exceptTime(String time) {
        while (true) {
            try {
                System.out.print(time);
                return LocalTime.parse(scan.nextLine());
            } catch (DateTimeParseException e) {
                System.out.print("Invalid Time ");
            }
        }

    }

    public static LocalDate exceptDate(String date) {
        while(true) {
            try{
                System.out.print(date);
                return LocalDate.parse(scan.nextLine());
            }catch (DateTimeParseException e) {
                System.out.print("Invalid Date ");
            }
        }
    }
}
