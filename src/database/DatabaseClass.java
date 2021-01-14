/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.Main;
import static main.Main.isSameDay;
import models.Assignment;
import models.Course;
import models.CourseAssignment;
import models.CourseStudent;
import models.CourseTrainer;
import models.Student;
import models.Trainer;
import models.ValidateDate;

/**
 *
 * @author vaggelis
 */
public class DatabaseClass {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/privateschool?";//serverTimezone=UTC +3";
    private static final String USERNAME = "JAVA_USER_1";
    private static final String PASSWORD = "12345";

    public static List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            System.out.println("successful connention ");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("connection created successfully");

            statement = connection.createStatement();
            String query = "SELECT studentId,firstName,lastName,dateOfBirth FROM STUDENT";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int s_id = resultSet.getInt("studentId");
                String f_name = resultSet.getString("firstName");
                String l_name = resultSet.getString("lastName");
                Date d = resultSet.getDate(4);
                LocalDate sd = Instant.ofEpochMilli(d.getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate();

                Student student = new Student(s_id, f_name, l_name, sd);
                students.add(student);

                System.out.println("student id: " + s_id + " first name: " + f_name + " last name: " + l_name);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR");

        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return students;
    }

    public static List<Trainer> selectAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Class.forName(MYSQL_JDBC_DRIVER);
            System.out.println("driver found");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();
            String query = "SELECT trainerId,firstName,lastName,subject FROM TRAINER";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int tr_id = resultSet.getInt("trainerId");
                String f_name = resultSet.getString("firstName");
                String l_name = resultSet.getString("lastName");
                String sub = resultSet.getString("subject");

                Trainer trainer = new Trainer(tr_id, f_name, l_name, sub);
                trainers.add(trainer);

                System.out.println("trainer_id: " + tr_id + " first_name: " + f_name + " last_name: " + l_name + " subject: " + sub);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return trainers;
    }

    public static List<Course> selectAllCourses() {
        List<Course> courses = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            System.out.println("driver found");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();
            String query = "SELECT courseId,title,stream,type,startDate,endDate FROM course";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int c_id = resultSet.getInt("courseId");
                String t = resultSet.getString("title");
                String str = resultSet.getString("stream");
                String type = resultSet.getString("type");
                Date std = resultSet.getDate("startDate");

                LocalDate sd = Instant.ofEpochMilli(std.getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                Date end = resultSet.getDate("endDate");
                LocalDate ed = Instant.ofEpochMilli(end.getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println("course_id: " + c_id + "   title: " + t + "   stream: " + str + "   type " + type + "   start date: " + sd
                        + "   end date: " + ed);

                Course course = new Course(c_id, t, str, type, sd, ed);
                courses.add(course);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return courses;
    }

    public static List<Assignment> selectAllAssignments() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Assignment> assignments = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT assignmentId,title,description,subDateTime,oralMark,totalMark FROM assignment";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int a_id = resultSet.getInt("assignmentId");
                String t = resultSet.getString("title");
                String desc = resultSet.getString("description");
                Date subDate = resultSet.getDate("subDateTime");

                LocalDate sd = Instant.ofEpochMilli(subDate.getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                double om = resultSet.getDouble(5);
                double tm = resultSet.getDouble(6);
                System.out.println("assignmentId: " + a_id + "   title: " + t + "    description: " + desc + "        submission date: "
                        + sd + "       oral mark: " + om + "        total mark:  " + tm);
                Assignment assignment = new Assignment(a_id, t, desc, sd, om, tm);
                assignments.add(assignment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return assignments;
    }

    public static List<CourseStudent> selectAllStudentsPerCourse(int course_id) {
        List<CourseStudent> courseStudents = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            System.out.println("successful connention ");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("connection created successfully");

            String query = "SELECT cs.course_studentId,cs.courseId, s.studentId,s.firstName,s.lastName FROM student as s,course_student as cs where s.studentId=cs.studentId and cs.courseId=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int cs_id = resultSet.getInt(1);
                int c_id = resultSet.getInt("cs.courseId");
                int s_id = resultSet.getInt("studentId");
                String f_name = resultSet.getString("firstName");
                String l_name = resultSet.getString("lastName");

                CourseStudent courseStudent = new CourseStudent(cs_id, course_id, s_id);
                courseStudents.add(courseStudent);
                System.out.println("course id:  " + c_id + "  student id: " + s_id + " first name: " + f_name + " last name: " + l_name);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR");

        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return courseStudents;
    }

    public static void selectAllTrainersPerCourse(int course_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            System.out.println("driver found");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT ct.courseId,t.trainerId,t.firstName,t.lastName,t.subject FROM TRAINER as t,course_trainer as ct where t.trainerId=ct.trainerId and ct.courseId=?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, course_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int c_id = resultSet.getInt("ct.courseId");
                int tr_id = resultSet.getInt("trainerId");
                String f_name = resultSet.getString("firstName");
                String l_name = resultSet.getString("lastName");
                String sub = resultSet.getString("subject");

                System.out.println("course id:  " + c_id + "  trainer_id: " + tr_id + " first_name: " + f_name + " last_name: " + l_name + " subject: " + sub);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static List<CourseAssignment> selectAllAssignmentsPerCourse(int course_id) {
        List<CourseAssignment> courseAssignments = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            System.out.println("driver found");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT ca.course_assignmentId,ca.courseId,a.assignmentId,a.title,a.description FROM assignment as a,course_assignment as ca where a.assignmentId=ca.assignmentId and ca.courseId=?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, course_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ca_id = resultSet.getInt(1);
                int c_id = resultSet.getInt("ca.courseId");
                int a_id = resultSet.getInt("assignmentId");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                CourseAssignment courseAssignment = new CourseAssignment(ca_id, course_id, a_id);
                courseAssignments.add(courseAssignment);
                System.out.println("course id:  " + c_id + "  assignment id: " + a_id + " title: " + title + " description: " + desc);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return courseAssignments;
    }

    public static void selectAllAssignmentsPerCoursePerStudent(int course_id, int student_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            System.out.println("driver found");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "select s.studentId,s.firstName,c.courseId,s.lastName,c.title,a.title,a.description\n"
                    + "from assignment as a\n"
                    + "join course_assignment as ca on a.assignmentId=ca.assignmentId\n"
                    + "join course as c on ca.course_assignmentId=c.courseId and c.courseId=?\n"
                    + "join course_student as cs on cs.courseId=c.courseId\n"
                    + "join student as s on cs.studentId=s.studentId\n"
                    + "and s.studentId=?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, course_id);
            preparedStatement.setInt(2, student_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int c_id = resultSet.getInt("c.courseId");
                String c_title = resultSet.getString("title");
                int s_id = resultSet.getInt("studentId");
                String fn = resultSet.getString("firstName");
                String ln = resultSet.getString("lastName");
                String at = resultSet.getNString(6);

                System.out.println("course id:  " + c_id + "    course_title:   " + c_title + "   student id:   " + s_id
                        + "   first name:   " + fn + "    last name: " + ln + "    assignment title: " + at);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void selectAllStudentsToMoreThanOneCourses() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            System.out.println("driver found");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "select s.studentId,s.firstName,s.lastName\n"
                    + "from course_student as cs\n"
                    + "join student as s on s.studentId=cs.studentId\n"
                    + "group by cs.studentId\n"
                    + "having count(*)>1\n"
                    + "order by s.studentId";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int s_id = resultSet.getInt(1);

                String fn = resultSet.getString(2);
                String ln = resultSet.getString(3);

                System.out.println("student id:  " + s_id + "    first name:   " + fn + "   last name:   " + ln);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public int insertStudent() throws ParseException {
        int rowsAffected = 0;
        boolean studentExists = false;
        Scanner scanner = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        boolean validString = false;
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε το όνομα του νέου μαθητή");
            firstName = scanner.next();
            validString = Main.isStringOnlyAlphabet(firstName);
        }
        validString = false;
        while (validString == false) {

            System.out.println("Παρακαλώ εισάγετε το επώνυμο του νέου μαθητή");
            lastName = scanner.next();
            validString = Main.isStringOnlyAlphabet(lastName);
        }

        String dateBirth = " ";
        boolean dateValid = false;

        while (dateValid == false) {
            System.out.println("Παρακαλώ εισάγετε την ημερομηνία γέννησης του νέου μαθητή dd-mm-yyyy");

            dateBirth = scanner.next();
            dateValid = ValidateDate.isValidDate(dateBirth);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(dateBirth, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        int tuitionFees = -1;
        String fees = " ";
        boolean validNumber = false;
        while (validNumber == false) {
            System.out.println("Παρακαλώ εισάγετε τα δίδακτρα του νέου μαθητή");
            fees = scanner.next();

            validNumber = Main.isValidNumeric(fees);
        }
        tuitionFees = Integer.parseInt(fees);
        //System.out.println("Οι παρακάτω μαθητές υπάρχουν ήδη στην βάση");
        List<Student> students = selectAllStudents();

        for (int i = 0; i < students.size(); i++) {
            LocalDate d = students.get(i).getDateOfBirth();
            java.sql.Date sqlDate2 = java.sql.Date.valueOf(d);

            if ((students.get(i).getFirstName().equals(firstName)) && (students.get(i).getLastName().equals(lastName)) && isSameDay(sqlDate2, sqlDate)) {
                System.out.println("O μαθητής είναι καταχωρημένος στην βάση");
                studentExists = true;
                break;
            }

        }
        if (studentExists == false) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName(MYSQL_JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                String query = " INSERT INTO STUDENT(firstName, lastName,dateOfBirth,tuitionFees) VALUES (?, ?,?,?) ";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setInt(4, tuitionFees);
                System.out.println("SUCCESSFULL INSERT");

                rowsAffected = preparedStatement.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return rowsAffected;
    }

    public int insertTrainer() {
        int rowsAffected = 0;
        boolean trainerExists = false;
        String firstName = "";
        String lastName = "";
        String subject = "";
        boolean validString = false;
        Scanner scanner = new Scanner(System.in);
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε το όνομα του εκπαιδευτή: ");
            firstName = scanner.next();
            validString = Main.isStringOnlyAlphabet(firstName);
        }
        validString = false;
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε το επώνυμο του εκπαιδευτή: ");
            lastName = scanner.next();
            validString = Main.isStringOnlyAlphabet(lastName);
        }
        validString = false;
        while (validString == false) {

            System.out.println("Παρακαλώ εισάγετε το μάθημα που διδάσκει: ");
            subject = scanner.next();
            validString = Main.isStringOnlyAlphabet(subject);
        }
        //Trainer trainer2 = new Trainer(firstName, lastName, subject);
        System.out.println("Οι παρακάτω καθηγητές υπάρχουν ήδη στην βάση:");
        List<Trainer> trainers = selectAllTrainers();

        for (int i = 0; i < trainers.size(); i++) {

            if ((trainers.get(i).getFirstName().equals(firstName)) && (trainers.get(i).getLastName().equals(lastName)) && (trainers.get(i).getSubject().equals(subject))) {
                System.out.println("O καθηγητής είναι καταχωρημένος στην βάση");
                trainerExists = true;
            }
        }
        if (trainerExists == false) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName(MYSQL_JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                String query = " INSERT INTO TRAINER(firstName, lastName,subject) VALUES (?, ?,?) ";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, subject);

                rowsAffected = preparedStatement.executeUpdate();
                System.out.println("SUCCESSFULL INSERT");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return rowsAffected;
    }

    public int insertCourse() throws ParseException {
        int rowsAffected = 0;
        boolean courseExists = false;
        String courseType = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Παρακαλώ δώστε το ονομα του course: ");
        String title1;
        String type1;

        title1 = scanner.next();
        while (!(courseType.toLowerCase().equals("java")) && !(courseType.toLowerCase().equals("c#"))) {
            System.out.println("Παρακαλώ δώστε το ειδος του course:  ");
            courseType = scanner.next();
        }
        System.out.println("Παρακαλώ δώστε το είδος του προγράμματος");
        type1 = scanner.nextLine();
        type1 = scanner.nextLine();
        String startDate = " ";
        boolean dateValid = false;

        while (dateValid == false) {
            System.out.println("Παρακαλώ δώστε ημερομηνία έναρξης του προγραμματος με την μορφή dd-mm-yyyy");

            startDate = scanner.next();
            dateValid = ValidateDate.isValidDate(startDate);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(startDate, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        dateValid = false;
        String endDate = " ";
        while (dateValid == false) {
            System.out.println("Παρακαλώ δώστε ημερομηνία λήξης του προγραμματος με την μορφή dd-mm-yyyy");

            endDate = scanner.next();
            dateValid = ValidateDate.isValidDate(endDate);
        }
        LocalDate localDate2 = LocalDate.parse(endDate, formatter);
        java.sql.Date sqlDate2 = java.sql.Date.valueOf(localDate2);

        List<Course> courses = selectAllCourses();

        for (int i = 0; i < courses.size(); i++) {

            if (courses.get(i).getTitle().equals(title1)) {
                System.out.println("To τμήμα είναι καταχωρημένο στην βάση");
                courseExists = true;
            }
        }
        if (courseExists == false) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName(MYSQL_JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                String query = " INSERT INTO COURSE VALUES (NULL,?, ?,?,?,?) ";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, title1);
                preparedStatement.setString(2, courseType);
                preparedStatement.setString(3, type1);
                preparedStatement.setDate(4, sqlDate);
                preparedStatement.setDate(5, sqlDate2);

                rowsAffected = preparedStatement.executeUpdate();
                System.out.println("SUCCESSFULL INSERT");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return rowsAffected;
    }

    public int insertAssignment() throws ParseException {
        int rowsAffected = 0;
        boolean assignmentExists = false;

        Scanner scanner = new Scanner(System.in);

        boolean validString = false;

        System.out.println("Παρακαλώ εισάγετε τον τίτλο της εργασίας: ");
        String title1 = scanner.nextLine();

        String description1 = "";
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε την περιγραφή της εργασίας: ");

            description1 = scanner.nextLine();
            validString = Main.isStringOnlyAlphabet(description1);
        }

        boolean dateValid = false;
        String subDateTime1 = "";

        while (dateValid == false) {
            System.out.println("Παρακαλώ δώστε την ημερομηνία υποβολής με την μορφή dd-mm-yyyy: ");
            subDateTime1 = scanner.next();

            dateValid = ValidateDate.isValidDate(subDateTime1);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(subDateTime1, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        System.out.println("Παρακαλώ καταχωρήστε προφορική βαθμολόγηση");
        double oralMark1 = scanner.nextDouble();

        System.out.println("Παρακαλώ καταχωρήστε συνολική  βαθμολόγηση");
        double totalMark1 = scanner.nextDouble();

        List<Assignment> assignments = selectAllAssignments();

        for (int i = 0; i < assignments.size(); i++) {

            if ((assignments.get(i).getTitle().equals(title1)) && (assignments.get(i).getDescription().equals(description1))) {
                System.out.println("H εργασία είναι καταχωρημένη στην βάση");
                assignmentExists = true;
            }
        }
        if (assignmentExists == false) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName(MYSQL_JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                String query = " INSERT INTO ASSIGNMENT VALUES (NULL,?, ?,?,?,?) ";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, title1);
                preparedStatement.setString(2, description1);
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setDouble(4, oralMark1);
                preparedStatement.setDouble(5, totalMark1);

                rowsAffected = preparedStatement.executeUpdate();
                System.out.println("SUCCESSFULL INSERT");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return rowsAffected;
    }

    public static List<CourseStudent> selectAllCourseStudents() {
        List<CourseStudent> coursestudents = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            System.out.println("successful connention ");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("connection created successfully");

            statement = connection.createStatement();
            String query = "SELECT studentId,courseId FROM course_student";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int s_id = resultSet.getInt("studentId");
                int c_id = resultSet.getInt("courseId");

                CourseStudent coursestudent = new CourseStudent(c_id, s_id);
                coursestudents.add(coursestudent);

                System.out.println(" course id:  " + c_id + "   student id: " + s_id);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR");

        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return coursestudents;
    }

    public static List<CourseTrainer> selectAllCourseTrainers() {
        List<CourseTrainer> courseTrainers = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            System.out.println("successful connention ");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("connection created successfully");

            statement = connection.createStatement();
            String query = "SELECT trainerId,courseId FROM course_trainer";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int t_id = resultSet.getInt("trainerId");
                int c_id = resultSet.getInt("courseId");

                CourseTrainer courseTrainer = new CourseTrainer(c_id, t_id);
                courseTrainers.add(courseTrainer);

                System.out.println(" course id:  " + c_id + "   trainer id: " + t_id);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR");

        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return courseTrainers;
    }

    public int insertStudentPerCourse() {
        int rowsAffected = 0;
        boolean course_student = false;

        List<Student> students = selectAllStudents();
        int chooseStudent = 0;
        boolean m = true;
        while (m == true) {
            System.out.println("Παρακαλώ διαλέξτε ID από τους μαθητές");
            Scanner sc = new Scanner(System.in);
            chooseStudent = sc.nextInt();
            for (int i = 0; i < students.size(); i++) {
                if (chooseStudent == students.get(i).getId()) {
                    m = false;
                    break;

                }

            }

        }
        m = true;
        int chooseCourse = 0;
        List<Course> courses = selectAllCourses();
        while (m == true) {
            System.out.println("Παρακαλώ διαλέξτε ID απo τα courses");
            Scanner sc = new Scanner(System.in);
            chooseCourse = sc.nextInt();
            for (int i = 0; i < courses.size(); i++) {
                if (chooseCourse == courses.get(i).getId()) {
                    m = false;
                    break;

                }

            }

        }

        List<CourseStudent> courseStudents = selectAllCourseStudents();

        for (int i = 0; i < courseStudents.size(); i++) {

            if ((courseStudents.get(i).getStudentId() == chooseStudent) && (courseStudents.get(i).getCourseId() == chooseCourse)) {
                System.out.println("H καταχώρηση υπάρχει ήδη στην βάση");
                course_student = true;
            }
        }
        if (course_student == false) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName(MYSQL_JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                String query = " INSERT INTO COURSE_STUDENT VALUES (NULL,?, ?) ";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, chooseStudent);
                preparedStatement.setInt(2, chooseCourse);

                rowsAffected = preparedStatement.executeUpdate();
                System.out.println("INSERT SUCCESSFULL");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
//
        return rowsAffected;
//        

    }

    public int insertTrainerPerCourse() {
        int rowsAffected = 0;
        boolean course_trainer = false;

        List<Trainer> trainers = selectAllTrainers();
        int chooseTrainer = 0;
        boolean m = true;
        while (m == true) {
            System.out.println("Παρακαλώ διαλέξτε ID από τους καθηγητές");
            Scanner sc = new Scanner(System.in);
            chooseTrainer = sc.nextInt();
            for (int i = 0; i < trainers.size(); i++) {
                if (chooseTrainer == trainers.get(i).getId()) {
                    m = false;
                    break;

                }

            }

        }
        m = true;
        int chooseCourse = 0;
        List<Course> courses = selectAllCourses();
        while (m == true) {
            System.out.println("Παρακαλώ διαλέξτε ID απo τα courses");
            Scanner sc = new Scanner(System.in);
            chooseCourse = sc.nextInt();
            for (int i = 0; i < courses.size(); i++) {
                if (chooseCourse == courses.get(i).getId()) {
                    m = false;
                    break;

                }

            }

        }

        List<CourseTrainer> courseTrainers = selectAllCourseTrainers();

        for (int i = 0; i < courseTrainers.size(); i++) {

            if ((courseTrainers.get(i).getTrainerId() == chooseTrainer) && (courseTrainers.get(i).getCourseId() == chooseCourse)) {
                System.out.println("H καταχώρηση υπάρχει ήδη στην βάση");
                course_trainer = true;
            }
        }
        if (course_trainer == false) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName(MYSQL_JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                String query = " INSERT INTO COURSE_TRAINER VALUES (NULL,?, ?) ";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, chooseCourse);
                preparedStatement.setInt(2, chooseTrainer);

                rowsAffected = preparedStatement.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
//
        return rowsAffected;
//        

    }

    public int insertAssignmentPerCoursePerStudent(double mark,int courseAssignmentId, int courseStudentId) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(MYSQL_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = " INSERT INTO COURSE_STUDENT_ASSIGNMENT VALUES (NULL,?,?, ?) ";

            preparedStatement = connection.prepareStatement(query);
             preparedStatement.setDouble(1, mark);
            preparedStatement.setInt(2, courseStudentId);
            preparedStatement.setInt(3, courseAssignmentId);

            rowsAffected = preparedStatement.executeUpdate();
            System.out.println("INSERT SUCCESSFULL");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

//
        return rowsAffected;
//        

    }
}
