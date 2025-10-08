# 🏥 Hospital Management System with Database

A simple **Hospital Management System** built in **Java** using **JDBC (Java Database Connectivity)** and **MySQL**.  
This project allows users to **manage doctors, patients, and appointments** through a console-based interface.

---

## 🚀 Features

- 👩‍⚕️ **Doctor Management** — Add, view, update, and delete doctor information  
- 🧑‍🤝‍🧑 **Patient Management** — Add new patients, view their records, and manage their details  
- 📅 **Appointments** — Create and cancel appointments between doctors and patients  
- ⚙️ **Database Integration** — Uses MySQL for persistent data storage  
- 💡 **Exception Handling** — Includes basic error handling for input and database operations  
- 🧩 **Modular Design** — Organized by packages (`dao`, `model`, `util`, `app`) following OOP principles  

---

## 🗂️ Project Structure

HospitalManagementSystemWithDB/
├── src/
│   ├── app/                # Main application entry point
│   │   └── Main.java
│   ├── dao/                # Data Access Objects for database operations
│   │   ├── doctorsDAO.java
│   │   ├── patientDAO.java
│   │   └── appointmentsDAO.java
│   ├── model/              # Java classes (entities)
│   │   ├── doctors.java
│   │   ├── patients.java
│   │   └── appointments.java
│   └── util/               # Utilities and helpers
│       ├── DBConnection.java
│       ├── ExceptionHandling.java
│       ├── ConvertSqlDateTimeToLocal.java
│       └── Menu.java
│
├── config.properties       # Database credentials (not uploaded to GitHub)
├── .gitignore
└── README.md

git clone https://github.com/Lukyshi/HospitalManagementSystemWithDB.git
cd HospitalManagementSystemWithDB

db.url=jdbc:mysql://localhost:3306/hospitaldb
db.username=root
db.password=yourpassword
