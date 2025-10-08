package util;

import model.patients;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.List;

import dao.appointmentsDAO;
import dao.doctorsDAO;
import dao.patientDAO;
import model.appointments;
import model.doctors;

public class Menu {

    private final static Scanner scan = new Scanner(System.in);
    private final static appointmentsDAO appDao = new appointmentsDAO();
    private final static doctorsDAO docDao = new doctorsDAO();
    private final static patientDAO patDao = new patientDAO();

    public static void mainMenu() {
        System.out.println("\nHospitalManagementSystem");
        System.out.println("1. Register patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. Book Appointment");

        System.out.println("4. View PatientsInfo");
        System.out.println("5. View DoctorsInfo");
        System.out.println("6. View Appointment");
        System.out.println("7. View AppointmentsByPatient");
        System.out.println("8. View AppointmentByDoctors");

        System.out.println("9. Update patient");
        System.out.println("10. Update Doctor");
        System.out.println("11. Update Appointment");

        System.out.println("12. Delete patient");
        System.out.println("13. Delete Doctor");
        System.out.println("14. Cancel Appointment");

        System.out.println("15. Exit");
    }

    public static void addPatientOperator() {
        System.out.print("Enter Name : ");
        String name = scan.nextLine();

        System.out.print("Enter Gender : ");
        String gender = scan.nextLine();

        System.out.print("Enter Age : ");
        int age = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter Contact Number : ");
        String contact_number = scan.nextLine();


        System.out.print("Enter Municipality : ");
        String municipality = scan.nextLine();

        System.out.print("Enter your Disease : ");
        String disease = scan.nextLine();

        patDao.addPatients(new patients(name, gender, age, contact_number, municipality, disease));
    }

    public static void addDoctorOperator() {
        System.out.print("Enter Name : ");
        String doctors_name = scan.nextLine();

        System.out.print("Enter Specialization : ");
        String specialization = scan.nextLine();

        System.out.print("Enter Contact Number : ");
        String doc_number = scan.nextLine();

        LocalDate schedule_date = LocalDate.now();

        docDao.addDoctors(new doctors(doctors_name, specialization, doc_number, schedule_date));
    }

    public static void bookAppointmentOperator() {
        System.out.print("Enter patients_id : ");
        int p_id = scan.nextInt();

        System.out.print("Enter doctors_id : ");
        int d_id = scan.nextInt();

        LocalDate appointment_date = LocalDate.now();

        LocalTime appointment_time = LocalTime.now();

        scan.nextLine();

        System.out.print("Enter Status : Done / Pending / Cancelled : ");
        String status = scan.nextLine();

        appDao.bookAppointment(new appointments(p_id, d_id, appointment_date, appointment_time, status));
    }

    public static void viewPatientInfoOP() {
        List<patients> patientsList = patDao.viewPatients();
        if(patientsList.isEmpty()) {
            System.out.print("Patients not found");
        }else {
            for(patients p : patientsList) {
                System.out.println(p.toString());
            }
        }
    }

    public static void viewDoctorsInfoOP() {
        List<doctors> doctorsList = docDao.viewAllDoctors();
        if(doctorsList.isEmpty()) {
            System.out.print("Doctors not found");
        }else{
            for(doctors d : doctorsList) {
                System.out.println(d.toString());
            }
        }
    }

    public static void viewAppointmentOP() {
        List<appointments> appointmentsList = appDao.viewAppointments();
        if(appointmentsList.isEmpty()) {
            System.out.print("No appointments available");
        }else{
            for(appointments ap : appointmentsList) {
                System.out.println(ap.toString());
            }
        }
    }

    public static void viewAppointmentByPatientOP() {
        System.out.print("Enter patients id to view appointment : ");
        int patients_id = scan.nextInt();

        patients patients = appDao.viewAppointmentByPatients(patients_id);

        if(patients == null) {
            System.out.print("patients don't have any appointment");
        }else{
            System.out.println(patients.toPatientString());
        }
    }

    public static void viewAppointmentByDoctorsOP() {
        System.out.print("Enter doctors id to view appointment : ");
        int doctors_id = scan.nextInt();;

        doctors doctors = appDao.viewAppointmentsByDoctors(doctors_id);

        if(doctors == null) {
            System.out.print("patients doesn't exist");
        }else {
            System.out.println(doctors.toDoctorsString());
        }
    }

    public static void updatePatientOP() {
        System.out.print("Enter patient id you want to update :");
        int patient_id = scan.nextInt();

        scan.nextLine();

        System.out.print("Update Name : ");
        String Update_name = scan.nextLine();

        System.out.print("Update Gender : ");
        String Update_gender = scan.nextLine();

        System.out.print("Update Age : ");
        int Update_age = scan.nextInt();

        scan.nextLine();

        System.out.print("Update Contact Number : ");
        String Update_contact_number = scan.nextLine();

        System.out.print("Update Municipality : ");
        String Update_municipality = scan.nextLine();

        System.out.print("Update your Disease : ");
        String Update_disease = scan.nextLine();

        patDao.updatePatientsInfo(patient_id, Update_name, Update_gender, Update_age,
                Update_contact_number, Update_municipality, Update_disease);
    }

    public static void updateDoctorOP() {
        System.out.print("Enter doctors id you want to update : ");
        int doctor_id = scan.nextInt();

        scan.nextLine();

        System.out.print("Enter Name : ");
        String Update_doctors_name = scan.nextLine();

        System.out.print("Enter Specialization : ");
        String Update_specialization = scan.nextLine();

        System.out.print("Enter Contact Number : ");
        String Update_doc_number = scan.nextLine();

        // import exception handling for date and time I create;
        LocalDate schedule = ExceptionHandling.exceptDate("Update Schedule YYY-MM-DD : ");

        docDao.updateDoctorsInfo(doctor_id, Update_doctors_name, Update_specialization,
                    Update_doc_number, schedule);
    }

    public static void updateAppointmentOP() {
        System.out.print("Enter appointment id you want to update : ");
        int id = scan.nextInt();

        scan.nextLine();

        // import exception handling for date and time I create;
        LocalDate Updated_appointment_date = ExceptionHandling.exceptDate("Enter new appointment date : ");
        LocalTime Updated_appointment_time = ExceptionHandling.exceptTime("Enter new appointment time : ");

        System.out.print("Enter Status : Done / Pending / Cancelled : ");
        String Update_status = scan.nextLine();

        appDao.updateAppointment(id, Updated_appointment_date, Updated_appointment_time,
                    Update_status);
    }

    public static void deletePatientOP() {
        System.out.print("Enter Patient ID you want to remove : ");
        int p_id_remove = scan.nextInt();

        patDao.deletePatientsRecord(p_id_remove);
    }

    public static void deleteDoctorOP() {
        System.out.print("Enter Doctor ID you want to remove : ");
        int d_id_remove = scan.nextInt();

        docDao.deleteDoctorsInfo(d_id_remove);
    }

    public static void cancelAppointmentOP() {
        System.out.print("Enter Appointment ID you want to cancel : ");
        int a_id_remove = scan.nextInt();

        appDao.cancelAppointment(a_id_remove);
    }
}
