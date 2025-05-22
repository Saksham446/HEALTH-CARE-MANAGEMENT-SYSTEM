HealthCare-Management-System

The Online Healthcare Management System is a comprehensive web application designed to improve healthcare services by connecting patients, doctors, and administrative staff in an efficient and user-friendly way. The system allows patients to register, book appointments, and manage their appointment details, while doctors can manage their schedules and view patient information. The platform also provides administrative staff with tools to manage patients and doctors.

Technologies Used Frontend: HTML, CSS, Bootstrap, JavaScript, FontAwesome Backend: Java Servlets, JSP Database: MySQL Server: Apache Tomcat Tools: Eclipse (IDE), MySQL Workbench

The system uses Java Servlets for backend processing, JSP for dynamic page generation, and MySQL for data storage. The frontend is designed using HTML, CSS, JavaScript, Bootstrap, and FontAwesome, providing a responsive and user-friendly interface. Apache Tomcat serves as the web server for deployment.

The goal of this project is to simplify the management of healthcare services, improve communication between patients and healthcare providers, and ensure secure data handling.

Key Features Patient Management: Enables patients to register, view their details, and manage appointments. Doctor Management: Allows admins to add, view, and update doctor details and schedules. Appointment Scheduling: Patients can search for available doctors and book appointments. Database Storage: All relevant data such as patient records, doctor information, and appointments are securely stored in a MySQL database. Responsive Frontend: Built using Bootstrap for a mobile-friendly and intuitive design. How the System Works Project Workflow Patient Registration and Login: Patients can create an account by entering their personal details. Once registered, they can log in and access features such as booking appointments and viewing their appointment details.

Doctor Management: Admin users can add and manage doctor profiles, specifying essential information such as name, specialty, and availability. Doctors can log in to view their schedules and patient appointments.

Appointment Scheduling: Patients can search for available doctors based on specialty and schedule appointments. Each appointment is recorded with details such as date, doctor, and patient.

Data Management and Security: The application relies on Java Servlets for backend operations such as user authentication, data management, and handling appointment-related actions. JSP is used for dynamic page generation. All data is securely stored and managed in a MySQL database.

.settings                           # IDE-specific configuration files (e.g., Eclipse, IntelliJ)
src/main
├── java/com
│   ├── admin/servlet                # Admin-related servlets that handle admin functionality (e.g., login, doctor management)
│   │   ├── AddDoctor.java           # Handles adding a new doctor
│   │   ├── AddSpecialist.java       # Handles adding a new specialist
│   │   ├── AdminLogin.java          # Handles admin login functionality
│   │   ├── AdminLogout.java         # Handles admin logout functionality
│   │   ├── DeleteDoctor.java        # Handles deleting a doctor
│   │   ├── UpdateDoctor.java        # Handles updating doctor information
│   ├── dao                          # Data Access Object (DAO) classes to handle database interactions
│   │   ├── AppointmentDAO.java      # Handles database operations related to appointments
│   │   ├── AppointmentDAOTest.java  # Unit test class for AppointmentDAO
│   │   ├── DoctorDao.java           # Handles database operations related to doctors
│   │   ├── DoctorDaoTest.java       # Unit test class for DoctorDao
│   │   ├── SpecialistDao.java       # Handles database operations related to specialists
│   │   ├── SpecialistDaoTest.java   # Unit test class for SpecialistDao
│   │   ├── UserDao.java             # Handles database operations related to users
│   │   ├── UserDaoTest.java         # Unit test class for UserDao
│   ├── db                           # Classes related to database connections
│   │   └── DBConnect.java           # Manages database connection
│   ├── doctor/servlet               # Doctor-related servlets for doctor-specific functionality
│   │   ├── DoctorPasswordChange.java # Allows a doctor to change their password
│   │   ├── DoctorLogin.java         # Handles doctor login functionality
│   │   ├── DoctorLogout.java        # Handles doctor logout functionality
│   │   ├── EditProfile.java         # Allows a doctor to edit their profile
│   │   ├── UpdateStatus.java        # Allows a doctor to update their status (e.g., available or not)
│   ├── entity                       # Entity classes representing the data model for the application
│   │   ├── Appointment.java         # Represents an appointment entity in the system
│   │   ├── Doctor.java              # Represents a doctor entity in the system
│   │   ├── Specialist.java          # Represents a specialist entity in the system
│   │   └── User.java                # Represents a user entity in the system
│   ├── user/servlet                 # User-related servlets that handle user functionality
│   │   ├── AppointmentServlet.java  # Handles user appointment actions (e.g., book, view, cancel)
│   │   ├── UserLogin.java           # Handles user login functionality
│   │   ├── UserLogout.java          # Handles user logout functionality
│   │   ├── UserRegister.java        # Handles user registration functionality
│   │   └── ChangePassword.java      # Allows a user to change their password
webapp
├── WEB-INF
│   ├── admin                        # JSP files related to the admin section of the application
│   │   ├── doctor.jsp               # Displays doctor information to the admin
│   │   ├── edit_doctor.jsp          # Allows the admin to edit doctor details
│   │   ├── index.jsp                # Admin homepage
│   │   ├── navbar.jsp               # Navbar used in the admin section
│   │   ├── patient.jsp              # Displays patient information to the admin
│   │   └── view_doctor.jsp          # Admin view of doctor details
│   ├── component                    # Shared JSP components (e.g., CSS, footer)
│   │   ├── allcss.jsp               # Contains global CSS for styling
│   │   ├── footer.jsp               # Footer section for all pages
│   │   └── navbar.jsp               # Shared navbar component for navigation
│   ├── doctor                       # JSP files related to the doctor section of the application
│   │   ├── comment.jsp              # Allows doctors to add comments
│   │   ├── edit_profile.jsp         # Allows doctors to edit their profile
│   │   ├── index.jsp                # Doctor homepage
│   │   ├── navbar.jsp               # Navbar used in the doctor section
│   │   ├── patient.jsp              # Displays patient information to the doctor
│   ├── img                          # Image resources
│   ├── Schema.sql                   # SQL file for setting up the database schema (tables, relationships)
│   ├── admin_login.jsp              # Login page for the admin section
│   ├── change_password.jsp          # Page for changing the password (user/doctor/admin)
│   ├── doctor_login.jsp             # Login page for doctors
│   ├── edit_profile.jsp             # Page for editing user/doctor profile
│   ├── index.jsp                    # Homepage for the user/doctor/admin section
│   ├── search.jsp                   # Page for searching doctors and specialists
│   ├── signup.jsp                   # User registration page
│   ├── user_appointment.jsp         # User appointment management page
│   ├── user_login.jsp               # Login page for users
│   └── view_appointment.jsp         # Page for viewing user appointments
target
├── .classpath                       # Classpath file for project dependencies
├── .gitignore                       # Git ignore file to exclude files from version control
├── .project                         # Project configuration file (IDE-specific)
├── README.md                        # Project documentation file
└── pom.xml                          # Maven project configuration file (dependencies, build settings)
