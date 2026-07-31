import java.util.*;

public class CollegeManagementApp {

    enum Department {
        EEE, ECE, CSE, IT, MECH, CIVIL
    }

    static abstract class User {
        protected final String userId;
        protected final String name;
        protected final String password;
        protected final String email;
        protected final String role;

        protected User(String userId, String name, String password, String email, String role) {
            this.userId = userId;
            this.name = name;
            this.password = password;
            this.email = email;
            this.role = role;
        }

        public String getUserId() { return userId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public boolean verifyPassword(String pwd) { return password.equals(pwd); }
        public abstract void showMenu();
    }

    static class Student extends User {
        private final String rollNo;
        private final Department department;
        private double cgpa;
        private int attendance;
        private boolean feePaid;
        private final Map<String, Double> marks = new LinkedHashMap<>();

        Student(String userId, String name, String password, String email, String rollNo, Department department) {
            super(userId, name, password, email, "STUDENT");
            this.rollNo = rollNo;
            this.department = department;
        }

        public String getRollNo() { return rollNo; }
        public Department getDepartment() { return department; }
        public double getCgpa() { return cgpa; }
        public int getAttendance() { return attendance; }
        public boolean isFeePaid() { return feePaid; }
        public Map<String, Double> getMarks() { return marks; }

        public void setCgpa(double cgpa) { this.cgpa = cgpa; }
        public void setAttendance(int attendance) { this.attendance = attendance; }
        public void setFeePaid(boolean feePaid) { this.feePaid = feePaid; }
        public void addMark(String subject, double mark) { marks.put(subject, mark); }

        @Override
        public void showMenu() {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. View Performance");
            System.out.println("3. View Marks");
            System.out.println("4. Fee Status");
            System.out.println("5. Logout");
        }
    }

    static class Teacher extends User {
        private final Department department;
        private final List<String> subjects = new ArrayList<>();

        Teacher(String userId, String name, String password, String email, Department department) {
            super(userId, name, password, email, "TEACHER");
            this.department = department;
        }

        public Department getDepartment() { return department; }
        public List<String> getSubjects() { return subjects; }
        public void addSubject(String subject) {
            if (!subjects.contains(subject)) subjects.add(subject);
        }

        @Override
        public void showMenu() {
            System.out.println("\n=== Teacher Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. View Department Students");
            System.out.println("3. Update Marks");
            System.out.println("4. Update Attendance");
            System.out.println("5. View Student Details");
            System.out.println("6. Logout");
        }
    }

    static class HOD extends User {
        private final Department department;
        private final List<String> teacherIds = new ArrayList<>();
        private final List<String> studentIds = new ArrayList<>();

        HOD(String userId, String name, String password, String email, Department department) {
            super(userId, name, password, email, "HOD");
            this.department = department;
        }

        public Department getDepartment() { return department; }
        public List<String> getTeacherIds() { return teacherIds; }
        public List<String> getStudentIds() { return studentIds; }
        public void addTeacher(String id) { if (!teacherIds.contains(id)) teacherIds.add(id); }
        public void addStudent(String id) { if (!studentIds.contains(id)) studentIds.add(id); }

        @Override
        public void showMenu() {
            System.out.println("\n=== HOD Menu ===");
            System.out.println("1. Department Overview");
            System.out.println("2. View Teachers");
            System.out.println("3. View Students");
            System.out.println("4. View Student Details");
            System.out.println("5. Logout");
        }
    }

    static class Admin extends User {
        Admin(String userId, String name, String password, String email) {
            super(userId, name, password, email, "ADMIN");
        }

        @Override
        public void showMenu() {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Overview");
            System.out.println("2. View All Students");
            System.out.println("3. View Fee Defaulters");
            System.out.println("4. Reports");
            System.out.println("5. Logout");
        }
    }

    static class CollegeSystem {
        private final Map<String, User> users = new LinkedHashMap<>();
        private final Map<String, Student> students = new LinkedHashMap<>();
        private final Map<String, Teacher> teachers = new LinkedHashMap<>();
        private final Map<String, HOD> hods = new LinkedHashMap<>();
        private User loggedInUser;

        CollegeSystem() {
            seedData();
        }

        private void seedData() {
            Admin admin1 = new Admin("ADMIN001", "Dr. Smith", "admin123", "admin@college.edu");
            Admin admin2 = new Admin("ADMIN002", "Dr. Johnson", "admin123", "admin2@college.edu");
            users.put(admin1.getUserId(), admin1);
            users.put(admin2.getUserId(), admin2);

            for (Department d : Department.values()) {
                HOD hod = new HOD("HOD_" + d.name(), "Prof. " + d.name(), "hod123", "hod@college.edu", d);
                hods.put(hod.getUserId(), hod);
                users.put(hod.getUserId(), hod);
            }

            addTeacher("TECH001", "Mr. Kumar", "teacher123", "tech1@college.edu", Department.CSE, "DSA", "Java");
            addTeacher("TECH002", "Mrs. Sharma", "teacher123", "tech2@college.edu", Department.CSE, "DBMS");
            addTeacher("TECH003", "Mr. Patel", "teacher123", "tech3@college.edu", Department.ECE, "Networks");
            addTeacher("TECH004", "Ms. Singh", "teacher123", "tech4@college.edu", Department.IT, "Web Tech");

            addStudent("STU001", "Arjun", "student123", "stu1@college.edu", "21001", Department.CSE);
            addStudent("STU002", "Divya", "student123", "stu2@college.edu", "21002", Department.CSE);
            addStudent("STU003", "Rohan", "student123", "stu3@college.edu", "21101", Department.ECE);
            addStudent("STU004", "Anjali", "student123", "stu4@college.edu", "21201", Department.IT);

            Student s1 = students.get("STU001");
            if (s1 != null) {
                s1.addMark("DSA", 85);
                s1.addMark("DBMS", 90);
                s1.setCgpa(3.8);
                s1.setAttendance(92);
                s1.setFeePaid(true);
            }

            Student s2 = students.get("STU002");
            if (s2 != null) {
                s2.addMark("DSA", 78);
                s2.setCgpa(3.5);
                s2.setAttendance(85);
                s2.setFeePaid(false);
            }

            for (Teacher teacher : teachers.values()) {
                HOD hod = hods.get("HOD_" + teacher.getDepartment().name());
                if (hod != null) hod.addTeacher(teacher.getUserId());
            }

            for (Student student : students.values()) {
                HOD hod = hods.get("HOD_" + student.getDepartment().name());
                if (hod != null) hod.addStudent(student.getUserId());
            }
        }

        private void addTeacher(String id, String name, String pwd, String email, Department department, String... subjects) {
            Teacher teacher = new Teacher(id, name, pwd, email, department);
            for (String subject : subjects) teacher.addSubject(subject);
            teachers.put(id, teacher);
            users.put(id, teacher);
        }

        private void addStudent(String id, String name, String pwd, String email, String rollNo, Department department) {
            Student student = new Student(id, name, pwd, email, rollNo, department);
            students.put(id, student);
            users.put(id, student);
        }

        public boolean login(String id, String password) {
            User user = users.get(id);
            if (user != null && user.verifyPassword(password)) {
                loggedInUser = user;
                return true;
            }
            return false;
        }

        public void logout() {
            loggedInUser = null;
        }

        public User getLoggedInUser() { return loggedInUser; }
        public boolean isLoggedIn() { return loggedInUser != null; }
        public Student getStudent(String id) { return students.get(id); }
        public Teacher getTeacher(String id) { return teachers.get(id); }

        public List<Student> getStudentsByDepartment(Department department) {
            List<Student> result = new ArrayList<>();
            for (Student student : students.values()) {
                if (student.getDepartment() == department) result.add(student);
            }
            return result;
        }

        public List<Teacher> getTeachersByDepartment(Department department) {
            List<Teacher> result = new ArrayList<>();
            for (Teacher teacher : teachers.values()) {
                if (teacher.getDepartment() == department) result.add(teacher);
            }
            return result;
        }

        public List<Student> getAllStudents() {
            return new ArrayList<>(students.values());
        }

        public List<Teacher> getAllTeachers() {
            return new ArrayList<>(teachers.values());
        }

        public int getUnpaidCount() {
            int count = 0;
            for (Student student : students.values()) {
                if (!student.isFeePaid()) count++;
            }
            return count;
        }

        public double getAverageCGPA() {
            if (students.isEmpty()) return 0;
            double sum = 0;
            for (Student student : students.values()) sum += student.getCgpa();
            return sum / students.size();
        }

        public void updateMarks(String studentId, String subject, double marks) {
            Student student = students.get(studentId);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }
            student.addMark(subject, marks);
            System.out.println("Marks updated successfully.");
        }

        public void updateAttendance(String studentId, int attendance) {
            Student student = students.get(studentId);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            if (attendance < 0 || attendance > 100) {
                System.out.println("Attendance must be between 0 and 100.");
                return;
            }
            student.setAttendance(attendance);
            System.out.println("Attendance updated successfully.");
        }

        public void printStudentDetails(Student student) {
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            System.out.println("\n--- Student Details ---");
            System.out.println("Name: " + student.getName());
            System.out.println("User ID: " + student.getUserId());
            System.out.println("Roll No: " + student.getRollNo());
            System.out.println("Department: " + student.getDepartment());
            System.out.println("Email: " + student.getEmail());
            System.out.println("CGPA: " + student.getCgpa());
            System.out.println("Attendance: " + student.getAttendance() + "%");
            System.out.println("Fee Status: " + (student.isFeePaid() ? "PAID" : "UNPAID"));
            System.out.println("Marks: " + student.getMarks());
        }
    }

    private final CollegeSystem system;
    private final Scanner scanner;

    public CollegeManagementApp() {
        system = new CollegeSystem();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new CollegeManagementApp().start();
    }

    public void start() {
        System.out.println("====================================");
        System.out.println("   COLLEGE MANAGEMENT SYSTEM v3.0   ");
        System.out.println("====================================");
        System.out.println("Type 'exit' at login to quit.\n");

        boolean running = true;
        while (running) {
            if (!system.isLoggedIn()) {
                running = loginPrompt();
            } else {
                handleLoggedInMenu();
            }
        }
        System.out.println("\nGoodbye.");
        scanner.close();
    }

    private boolean loginPrompt() {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        if (id.equalsIgnoreCase("exit")) return false;

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        if (system.login(id, password)) {
            System.out.println("Welcome, " + system.getLoggedInUser().getName() + "!\n");
        } else {
            System.out.println("Invalid login. Try again.\n");
        }
        return true;
    }

    private void handleLoggedInMenu() {
        User user = system.getLoggedInUser();
        user.showMenu();
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();

        if (user instanceof Student student) {
            handleStudentMenu(student, choice);
        } else if (user instanceof Teacher teacher) {
            handleTeacherMenu(teacher, choice);
        } else if (user instanceof HOD hod) {
            handleHODMenu(hod, choice);
        } else if (user instanceof Admin admin) {
            handleAdminMenu(admin, choice);
        }
    }

    private void handleStudentMenu(Student student, String choice) {
        switch (choice) {
            case "1" -> {
                System.out.println("\nName: " + student.getName());
                System.out.println("Roll No: " + student.getRollNo());
                System.out.println("Department: " + student.getDepartment());
                System.out.println("Email: " + student.getEmail());
            }
            case "2" -> {
                System.out.println("\nCGPA: " + student.getCgpa());
                System.out.println("Attendance: " + student.getAttendance() + "%");
                System.out.println("Fee Status: " + (student.isFeePaid() ? "PAID" : "UNPAID"));
            }
            case "3" -> System.out.println("\nMarks: " + student.getMarks());
            case "4" -> System.out.println(student.isFeePaid() ? "\nFee is PAID." : "\nFee is UNPAID.");
            case "5" -> {
                system.logout();
                System.out.println("Logged out successfully.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void handleTeacherMenu(Teacher teacher, String choice) {
        switch (choice) {
            case "1" -> {
                System.out.println("\nName: " + teacher.getName());
                System.out.println("Department: " + teacher.getDepartment());
                System.out.println("Subjects: " + teacher.getSubjects());
                System.out.println("Email: " + teacher.getEmail());
            }
            case "2" -> {
                System.out.println("\nStudents in " + teacher.getDepartment() + ":");
                for (Student student : system.getStudentsByDepartment(teacher.getDepartment())) {
                    System.out.println(student.getUserId() + " | " + student.getName() + " | " + student.getRollNo());
                }
            }
            case "3" -> {
                System.out.print("Student ID: ");
                String studentId = scanner.nextLine().trim();
                Student student = system.getStudent(studentId);
                if (student != null && student.getDepartment() == teacher.getDepartment()) {
                    System.out.print("Subject: ");
                    String subject = scanner.nextLine().trim();
                    System.out.print("Marks: ");
                    try {
                        double marks = Double.parseDouble(scanner.nextLine().trim());
                        system.updateMarks(studentId, subject, marks);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid marks.");
                    }
                } else {
                    System.out.println("Not allowed or student not found.");
                }
            }
            case "4" -> {
                System.out.print("Student ID: ");
                String studentId = scanner.nextLine().trim();
                Student student = system.getStudent(studentId);
                if (student != null && student.getDepartment() == teacher.getDepartment()) {
                    System.out.print("Attendance: ");
                    try {
                        int attendance = Integer.parseInt(scanner.nextLine().trim());
                        system.updateAttendance(studentId, attendance);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid attendance.");
                    }
                } else {
                    System.out.println("Not allowed or student not found.");
                }
            }
            case "5" -> {
                System.out.print("Student ID: ");
                String studentId = scanner.nextLine().trim();
                system.printStudentDetails(system.getStudent(studentId));
            }
            case "6" -> {
                system.logout();
                System.out.println("Logged out successfully.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void handleHODMenu(HOD hod, String choice) {
        switch (choice) {
            case "1" -> {
                System.out.println("\nDepartment: " + hod.getDepartment());
                System.out.println("Teachers: " + hod.getTeacherIds().size());
                System.out.println("Students: " + hod.getStudentIds().size());
            }
            case "2" -> {
                System.out.println("\nTeachers in " + hod.getDepartment() + ":");
                for (Teacher teacher : system.getTeachersByDepartment(hod.getDepartment())) {
                    System.out.println(teacher.getUserId() + " | " + teacher.getName());
                }
            }
            case "3" -> {
                System.out.println("\nStudents in " + hod.getDepartment() + ":");
                for (Student student : system.getStudentsByDepartment(hod.getDepartment())) {
                    System.out.println(student.getUserId() + " | " + student.getName());
                }
            }
            case "4" -> {
                System.out.print("Student ID: ");
                String studentId = scanner.nextLine().trim();
                system.printStudentDetails(system.getStudent(studentId));
            }
            case "5" -> {
                system.logout();
                System.out.println("Logged out successfully.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void handleAdminMenu(Admin admin, String choice) {
        switch (choice) {
            case "1" -> {
                System.out.println("\nTotal Students: " + system.getAllStudents().size());
                System.out.println("Total Teachers: " + system.getAllTeachers().size());
                System.out.println("Fee Defaulters: " + system.getUnpaidCount());
            }
            case "2" -> {
                System.out.println("\nAll Students:");
                for (Student student : system.getAllStudents()) {
                    System.out.println(student.getUserId() + " | " + student.getName() + " | " + student.getDepartment());
                }
            }
            case "3" -> {
                System.out.println("\nFee Defaulters:");
                for (Student student : system.getAllStudents()) {
                    if (!student.isFeePaid()) {
                        System.out.println(student.getUserId() + " | " + student.getName() + " | " + student.getDepartment());
                    }
                }
            }
            case "4" -> {
                System.out.println("\nAverage CGPA: " + String.format(Locale.US, "%.2f", system.getAverageCGPA()));
                System.out.println("Total Students: " + system.getAllStudents().size());
                System.out.println("Total Teachers: " + system.getAllTeachers().size());
            }
            case "5" -> {
                system.logout();
                System.out.println("Logged out successfully.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}
