/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import database.DatabaseClass;
import static database.DatabaseClass.selectAllAssignmentsPerCourse;
import static database.DatabaseClass.selectAllAssignmentsPerCoursePerStudent;
import static database.DatabaseClass.selectAllCourseStudents;
import static database.DatabaseClass.selectAllStudents;
import static database.DatabaseClass.selectAllStudentsPerCourse;
import static database.DatabaseClass.selectAllTrainersPerCourse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import models.Course;
import models.CourseAssignment;
import models.CourseStudent;
import models.Student;

public class Main {

    public static void main(String[] args) throws ParseException {
        DatabaseClass db = new DatabaseClass();
        String option = "";

        do {
            
            System.out.println("--------------------------");
            System.out.println("                          ");
            System.out.println("                          ");
            System.out.println("1. Select all students.");
            System.out.println("2. Select all trainers.");
            System.out.println("3. Select all courses.");
            System.out.println("4. Select all assignments.");
            System.out.println("5. Select all students per course");
            System.out.println("6. Select all trainers per course.");
            System.out.println("7. Select all assignments per course.");
            System.out.println("8. Select all the assignments per course per student.");
            System.out.println("9. Select  list of students that belong to more than one courses");
            System.out.println("10. Insert student.");
            System.out.println("11. Insert Trainer.");
            System.out.println("12. Insert assignment.");
            System.out.println("13. Insert course.");
            System.out.println("14. Insert Student per course.");
            System.out.println("15. Insert Trainer per Course.");
            System.out.println("16. Insert assignment per student per course.");
            //System.out.println("17. Select a list of the students that belong to more than one courses.");
            System.out.println("q. To quit.");

            Scanner scanner = new Scanner(System.in);
            option = scanner.next();

            DatabaseClass databaseClasses = new DatabaseClass();
            List<Course> courses;
            List<Student> students;
            switch (option) {
                case "1":
                    db.selectAllStudents();
                    break;
                case "2":
                    db.selectAllTrainers();
                    break;
                case "3":
                    db.selectAllCourses();
                    break;
                case "4":
                    db.selectAllAssignments();
                    break;
                case "5":

                    courses = db.selectAllCourses();
                    int choise = 0;
                    boolean ask = true;
                    while (ask == true) {
                        System.out.println("give course number ");
                        choise = scanner.nextInt();
                        for (int i = 0; i < courses.size(); i++) {
                            if (choise == courses.get(i).getId()) {
                                db.selectAllStudentsPerCourse(choise);
                                ask = false;
                                break;
                            }
                        }

                    }
                    break;
                case "6":
                    courses = db.selectAllCourses();
                    choise = 0;
                    ask = true;
                    while (ask == true) {
                        System.out.println("give course number ");
                        choise = scanner.nextInt();
                        for (int i = 0; i < courses.size(); i++) {
                            if (choise == courses.get(i).getId()) {
                                db.selectAllTrainersPerCourse(choise);
                                ask = false;
                                break;
                            }
                        }

                    }
                    break;
                case "7":
                    courses = db.selectAllCourses();
                    choise = 0;
                    ask = true;
                    while (ask == true) {
                        System.out.println("give course number ");
                        choise = scanner.nextInt();
                        for (int i = 0; i < courses.size(); i++) {
                            if (choise == courses.get(i).getId()) {
                                db.selectAllAssignmentsPerCourse(choise);
                                ask = false;
                                break;
                            }
                        }

                    }
                    break;
                case "8":
                    courses = db.selectAllCourses();
                    
                    choise = 0;
                    ask = true;
                    while (ask == true) {
                        System.out.println("give course number ");
                        choise = scanner.nextInt();
                        for (int i = 0; i < courses.size(); i++) {
                            if (choise == courses.get(i).getId()) {

                                ask = false;
                                break;
                            }
                        }

                    }
                    students = db.selectAllStudents();
                    int choise2 = 0;
                    boolean ask2 = true;
                    while (ask2 == true) {
                        System.out.println("give student number ");
                        choise2 = scanner.nextInt();
                        for (int i = 0; i < students.size(); i++) {
                            if (choise2 == students.get(i).getId()) {

                                ask2 = false;
                                break;
                            }
                        }

                    }
                    db.selectAllAssignmentsPerCoursePerStudent(choise, choise2);
                    break;
                case "9":
                 db.selectAllStudentsToMoreThanOneCourses();
                 break;
                case "10":
                 int rows_affected=db.insertStudent();
                    System.out.println(rows_affected+" rows affected");
                 break;
                case "11":
                 rows_affected=db.insertTrainer();
                    System.out.println(rows_affected+" rows affected");
                 break;
                case "12":
                    rows_affected=db.insertAssignment();
                 System.out.println(rows_affected+" rows affected");
                 break;
                case "13":
                 rows_affected=db.insertCourse();
                 System.out.println(rows_affected+" rows affected");
                 break;
                case "14":
                 rows_affected=db.insertStudentPerCourse();
                 System.out.println(rows_affected+" rows affected");
                 break;
                case "15":
                 rows_affected=db.insertTrainerPerCourse();
                 System.out.println(rows_affected+" rows affected");
                 break;
                case "16":
              
                   courses = db.selectAllCourses();
                    
                    choise = 0;
                    ask = true;
                    while (ask == true) {
                        System.out.println("give course number ");
                        choise = scanner.nextInt();
                        for (int i = 0; i < courses.size(); i++) {
                            if (choise == courses.get(i).getId()) {

                                ask = false;
                                break;
                            }
                        }

                    }  
                    
                    List<CourseAssignment> courseAssignments=selectAllAssignmentsPerCourse(choise);
                    
                    choise2 = 0;
                    ask = true;
                    int courseassignment_id=0;
                    while (ask == true) {
                        System.out.println("Please choose assignment_id");
                        choise2 = scanner.nextInt();
                        for (int i = 0; i < courseAssignments.size(); i++) {
                            if (choise2 == courseAssignments.get(i).getAssignmentId()) {
                                courseassignment_id=courseAssignments.get(i).getCourseAssignmentId();
                                ask = false;
                                break;
                            }
                        }

                    }  
                    
                    
                   List<CourseStudent> courseStudents=selectAllStudentsPerCourse(choise);
                   int choise3 = 0;
                   int coursestudent_Id=0;
                    ask = true;
                    while (ask == true) {
                        System.out.println("Please choose student_id");
                        choise3 = scanner.nextInt();
                        for (int i = 0; i < courseStudents.size(); i++) {
                            if (choise3 == courseStudents.get(i).getStudentId()) {
                                coursestudent_Id=courseStudents.get(i).getId();
                                ask = false;
                                break;
                            }
                        }

                    }  
                    
                    int mark=0;
                    String m="";
                    ask = false;
                    while (ask == false) {
                        System.out.println("Please give mark for the student's assignment");
                        m=scanner.next();
                        ask=isValidNumeric(m);
                        
                    }
                    mark= Integer.parseInt(m);
            int rowsAffected=db.insertAssignmentPerCoursePerStudent(mark,courseassignment_id,coursestudent_Id) ;
                    
                   
                    
                    
                    
                    
                    
                    
                    
                    
                
            }

        } while (!option.toLowerCase().equals("q"));
    }

    public static boolean isStringOnlyAlphabet(String str) {
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
    }

    public static boolean isValidNumeric(String str) {
        str = str.trim(); // trims the white spaces. 

        if (str.length() == 0) {
            return false;
        }

        // if string is of length 1 and the only 
        // character is not a digit 
        if (str.length() == 1 && !Character.isDigit(str.charAt(0))) {
            return false;
        }

        // If the 1st char is not '+', '-', '.' or digit 
        if (str.charAt(0) != '+' && str.charAt(0) != '-'
                && !Character.isDigit(str.charAt(0))
                && str.charAt(0) != '.') {
            return false;
        }

        // To check if a '.' or 'e' is found in given 
        // string. We use this flag to make sure that 
        // either of them appear only once. 
        boolean flagDotOrE = false;

        for (int i = 1; i < str.length(); i++) {
            // If any of the char does not belong to 
            // {digit, +, -, ., e} 
            if (!Character.isDigit(str.charAt(i))
                    && str.charAt(i) != 'e' && str.charAt(i) != '.'
                    && str.charAt(i) != '+' && str.charAt(i) != '-') {
                return false;
            }

            if (str.charAt(i) == '.') {
                // checks if the char 'e' has already 
                // occurred before '.' If yes, return 0. 
                if (flagDotOrE == true) {
                    return false;
                }

                // If '.' is the last character. 
                if (i + 1 >= str.length()) {
                    return false;
                }

                // if '.' is not followed by a digit. 
                if (!Character.isDigit(str.charAt(i + 1))) {
                    return false;
                }
            } else if (str.charAt(i) == 'e') {
                // set flagDotOrE = 1 when e is encountered. 
                flagDotOrE = true;

                // if there is no digit before 'e'. 
                if (!Character.isDigit(str.charAt(i - 1))) {
                    return false;
                }

                // If 'e' is the last Character 
                if (i + 1 >= str.length()) {
                    return false;
                }

                // if e is not followed either by 
                // '+', '-' or a digit 
                if (!Character.isDigit(str.charAt(i + 1))
                        && str.charAt(i + 1) != '+'
                        && str.charAt(i + 1) != '-') {
                    return false;
                }
            }
        }

        /* If the string skips all above cases, then 
           it is numeric*/
        return true;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

//   public static void selectAllStudentsPerCourse2(){
//       Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            Class.forName(MYSQL_JDBC_DRIVER);
//
//            System.out.println("successful connention ");
//            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//
//            System.out.println("connection created successfully");
//            Scanner sc = new Scanner(System.in);
//           
//            boolean validNumber = false;
//           String course_id="";
//            while (validNumber == false) {
//            System.out.println("Παρακαλώ δώστε το courseId για το οποίο θέλετε να δείτε τους μαθητές");
//            course_id = sc.next();
//
//            validNumber = Main.isValidNumeric(course_id);
//        }
//            
//            String query = "SELECT s.studentId,s.firstName,s.lastName FROM student as s,course_student as cs where s.studentId=cs.studentId and cs.courseId=?";
//                 // and cs.courseId is 3";
//            preparedStatement = connection.prepareStatement(query);
//           preparedStatement.setInt(1, 3);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int s_id = resultSet.getInt("studentId");
//                String f_name = resultSet.getString("firstName");
//                String l_name = resultSet.getString("lastName");
//
//                System.out.println("student id: " + s_id + " first name: " + f_name + " last name: " + l_name);
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("ERROR");
//
//        } finally {
//
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
}
