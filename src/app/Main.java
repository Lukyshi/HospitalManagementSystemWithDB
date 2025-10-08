package app;
import java.util.Scanner;
import util.Menu;

public class Main {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;

        do{
            Menu.mainMenu();
            System.out.print("Choose an option : ");
            choice = scan.nextInt();

            scan.nextLine();

            switch (choice){

                case 1 -> Menu.addPatientOperator();
                case 2 -> Menu.addDoctorOperator();
                case 3 -> Menu.bookAppointmentOperator();
                case 4 -> Menu.viewPatientInfoOP();
                case 5 -> Menu.viewDoctorsInfoOP();
                case 6 -> Menu.viewAppointmentOP();
                case 7 -> Menu.viewAppointmentByPatientOP();
                case 8 -> Menu.viewAppointmentByDoctorsOP();
                case 9 -> Menu.updatePatientOP();
                case 10 -> Menu.updateDoctorOP();
                case 11 -> Menu.updateAppointmentOP();
                case 12 -> Menu.deletePatientOP();
                case 13 -> Menu.deleteDoctorOP();
                case 14 -> Menu.cancelAppointmentOP();
                case 15 -> System.out.println("Exiting the program...");
                default -> System.out.println("Invalid choice");
            }

        }while(choice != 15);
        scan.close();
    }
}