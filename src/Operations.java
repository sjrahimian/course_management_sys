/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Interface between frontend and backend functions implementations
 *
 */

import customDatatypes.NotificationTypes;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.ModelRegister;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;

import java.util.*;

public class Operations {
    private List<ICourseOffering> enrollStuList = new ArrayList<>();
    private List<StudentModel> enrollCourseList = new ArrayList<>();
    private Enrollment enrollMe = new Enrollment();

    /**
     * helper for checking courses
     * @param stuID course to find
     */
    public static Boolean doesSoAndSoExist(String stuID){
        SystemUserModel user = ModelRegister.getInstance().getRegisteredUser(stuID);
        if(user != null){ //if there is such a course
            return true;
        }

        System.out.println("\nNo such user id: " + stuID);
        return false;

    }

    /**
     * finds a student and returns them
     * @param c course
     * @param studentID student id
     * @return
     */
    public StudentModel findStudent(CourseOffering c, String studentID){
        for (StudentModel s : c.getStudentsAllowedToEnroll()){
            if(s.getID().equals(studentID))
                return s;
        }

        return null;
    }

    /**
     * Load course registration files.
     */
    public void loadCourses() {
        BuildCourses newCourse = new BuildCourses();
        newCourse.runRegistration();

    }

    /**
     * Print class record.
     * @param courseID name of course user wants
     * @param instructorID user's ID
     */
    public void printRoster(String courseID, String instructorID) {
        Printer print = new Printer();
        print.classRecord(courseID,instructorID);
    }

    /**
     * Print course record for one student
     * @param courseID get the course id
     * @param studentID user's id
     */
    public void printStudentCourse(String courseID, String studentID){
        Printer print = new Printer();
        print.singleStudentsCourse(courseID,studentID);
    }

    /**
     * prints all courses that a student has been registered in
     * @param studentID student id
     */
    public void printAllStudentsCourses(String studentID){
        Printer print = new Printer();
        print.allStudentsCourses(studentID);
    }

    /**
     * enrolls a new student
     */
    public void enrollStudent(){
        Scanner input = new Scanner(System.in);

        System.out.print("\n\tCourse (e.g., \"CS2212B\") to enroll in: ");
        String c = input.next();
        c = c.toUpperCase();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(c);
        if(course == null){
            System.out.println("\nNo such course: " + c);
            return;
        }

        System.out.print("\tStudent ID to be enrolled: ");
        String s = input.next();

        if(doesSoAndSoExist(s))
            this.enrollMe.enroll_Student(course,s);

    }

    public void enrollStudentRequest(String studentID){
        Scanner input = new Scanner(System.in);

        System.out.print("\n\tCourse (e.g., \"CS2212B\") to enroll in: ");
        String c = input.next();
        c = c.toUpperCase();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(c);
        if(course == null){
            System.out.println("\nNo such course: " + c);
            return;
        }

        this.enrollMe.enroll_Student(course,studentID);

    }

    /**Add notification preferences.
     *
     * @param courseName get the course id
     * @param id user's id
     */
    public void setNotification(String courseName,String id){
        Scanner input = new Scanner(System.in);

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseName);
        if(course == null){
            System.out.println("\nNo such course.");
            return;
        }

        StudentModel student = findStudent(course, id);
        if(student == null){
            System.out.println("\nNo such student.");
            return;
        }

        System.out.print("\tGive Notification Type (\"EMAIL\", \"PHONE\", \"MAIL\"): ");
        String line = input.next();
        switch (line.toUpperCase()){
            case "EMAIL": student.setNotificationType(NotificationTypes.EMAIL);
                System.out.println("\nYou should receive emails from now on, unless it gets lost.");
                break;
            case "PHONE": student.setNotificationType(NotificationTypes.CELLPHONE);
                System.out.println("\nRing ring.");
                break;
            case "PIGEON": student.setNotificationType(NotificationTypes.PIGEON_POST);
                System.out.println("\nHand delivered to your door by a man dressed as a giant pigeon.");
                break;
            case "MAIL": student.setNotificationType(NotificationTypes.MAIL);
                System.out.println("\nWe are licking the stamps. Expect slobbery mail.");
                break;
            default:
                System.out.println("\nInvalid selection. Process aborted and returning to main.");

        }

    }

    /**
     * allows user to add new mark for a student
     */
    public void addStudentMark(){
        Scanner input = new Scanner(System.in);
        Marking submitMark = new Marking();

        System.out.print("\n\t\t::: Mark Management: Add :::\n\n\tEnter Student ID: ");
        String sID = input.next();

        System.out.print("\tEnter Course ID: ");
        String cID = input.next();

        System.out.print("\t** Following is case sensitive **\n\t"+
                "Enter type as 'Final', 'Midterm', or 'ASSIGNMENT-X': ");
        String typ = input.next();

        System.out.print("\tEnter grade received (Format as '0.0'): ");
        Double gra = input.nextDouble();

        submitMark.addMark(cID, sID, typ, gra);

    }

    /**
     * allows user to modify a previously entered mark
     */
    public void modifyMark(){
        Scanner input = new Scanner(System.in);
        Marking m = new Marking();

        System.out.print("\n\t\t::: Mark Management: Modify :::\n\n\tEnter Student ID: ");
        String sID = input.next();

        System.out.print("\tEnter Course ID: ");
        String cID = input.next();

        System.out.print("\t** Following is case sensitive **\n\t"+
                "Enter type as 'Final', 'Midterm', or 'ASSIGNMENT-x': ");
        String typ = input.next();

        System.out.print("\tEnter revised grade (Format as '0.0'): ");
        Double gra = input.nextDouble();

        m.updateMark(cID, sID, typ, gra);

    }

}


