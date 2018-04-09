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
import java.util.*;

public class Operations {
    List<ICourseOffering> enrollStuList = new ArrayList<>();
    List<StudentModel> enrollCourseList = new ArrayList<>();

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
     * Enrolls a student in course: adds student to course list, and course to student list
     * @param courseID course name
     * @param studentID user id
     */
    public void enroll_1_Student(String courseID, String studentID){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);
        if(course == null){
            System.out.println("\nNo such course: " + courseID);
            return;
        }

        StudentModel student = findStudent(course, studentID);
        if(student == null){
            System.out.println("\nNo such student: " + studentID);
            return;
        }

        Boolean registered = false;

        for(CourseOffering course2 : ModelRegister.getInstance().getAllCourses()){
            for(StudentModel student2 : course2.getStudentsAllowedToEnroll()){
                if(course2.getCourseID().equals(courseID) && student2.getID().equals(studentID)){
                    if(course2.getStudentsEnrolled().isEmpty()){
                        registered = true;

                        this.enrollStuList = new ArrayList<>();
                        this.enrollCourseList = new ArrayList<>();

                        enrollStuList.add(course2);
                        student2.setCoursesEnrolled(enrollStuList);

                        enrollCourseList.add(student2);
                        course2.setStudentsEnrolled(enrollCourseList);

                        System.out.println("\nEnrolling " + student2.getID() + " in " + course2.getCourseID());
                    }
                    else{
                        registered = true;

                        this.enrollStuList.add(course2);
                        student2.setCoursesEnrolled(this.enrollStuList);

                        this.enrollCourseList.add(student2);
                        course2.setStudentsEnrolled(this.enrollCourseList);

                        System.out.println("\nEnrolling " + student2.getID() + " in " + course2.getCourseID());
                    }
                }
            }
        }

        if(!registered){
            System.out.println("\n>>Denied enrollment<<");
        }


    }

    /**
     * Enrolls a student in course: adds student to course list, and course to student list
     * @param courseID course name
     * @param studentID user id
     */
    public void enroll_Student(String courseID, String studentID){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);
        if(course == null){
            System.out.println("\nNo such course: " + courseID);
            return;
        }

        StudentModel student = findStudent(course, studentID);
        if(student == null){
            System.out.println("\nNo such student: " + studentID);
            return;
        }

        Boolean registered = false;

        for(CourseOffering course2 : ModelRegister.getInstance().getAllCourses()){
            for(StudentModel student2 : course2.getStudentsAllowedToEnroll()){
                if(course2.getCourseID().equals(courseID) && student2.getID().equals(studentID)){
                    if(course2.getStudentsEnrolled().isEmpty()){
                        registered = true;

                        this.enrollStuList = new ArrayList<>();
                        this.enrollCourseList = new ArrayList<>();

                        enrollStuList.add(course2);
                        student2.setCoursesEnrolled(enrollStuList);

                        enrollCourseList.add(student2);
                        course2.setStudentsEnrolled(enrollCourseList);

                        System.out.println("\nEnrolling " + student2.getID() + " in " + course2.getCourseID());
                    }
                    else{
                        registered = true;

                        this.enrollStuList.add(course2);
                        student2.setCoursesEnrolled(this.enrollStuList);

                        this.enrollCourseList.add(student2);
                        course2.setStudentsEnrolled(this.enrollCourseList);

                        System.out.println("\nEnrolling " + student2.getID() + " in " + course2.getCourseID());
                    }
                }
            }
        }

        if(!registered){
            System.out.println("\n>>Denied enrollment<<");
        }


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


