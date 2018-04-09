import customDatatypes.NotificationTypes;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.ModelRegister;
import systemUsers.StudentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Operations {
    /**
     * helper for checking courses
     * @param cID course to find
     */
    public static Boolean doesCourseExist(String cID){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(cID);
        if(course != null){ //if there is such a course
            return true;
        }

        System.out.println("\nNo such course.");
        return false;

    }

    /**
     * helper for checking courses
     * @param stuID course to find
     */
    public static Boolean doesStudentExist(String stuID){

        //need to make sure that the stuID is not a instructor ID
//        SystemUserModel temp = ModelRegister.getInstance().getRegisteredUser(stuID);
//        if(temp.getID().equals(stuID));

        StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(stuID);


        if(student != null){ //if there is such a course
            return true;
        }

        System.out.println("\nNo such student.");
        return false;

    }

    /**
     * Load course registration files.
     */
    public static void loadCourses() {
        BuildCourses newCourse = new BuildCourses();
        newCourse.runRegistration();

    }

    /**
     * Print class record.
     * @param courseName name of course user wants
     * @param id user's ID
     */
    public static void printRoster(String courseName, String id) {
        Printer print = new Printer();
        if(doesCourseExist(courseName))
            print.classRecord(courseName,id);
    }

    /**
     * Print course record for one student
     * @param courseName get the course id
     * @param sID user's id
     */
    public static void printStudentCourse(String courseName, String sID){
        Printer print = new Printer();

        if(doesCourseExist(courseName))
            print.singleStudentsCourse(courseName,sID);
    }

    /**
     * prints all courses that a student has been registered in
     * @param sID student id
     */
    public static void printAllStudentsCourses(String sID){
        Printer print = new Printer();
        print.allStudentsCourses(sID);
    }

    /**
     * Enrolls a student in course: adds student to course list, and course to student list
     * @param cID course name
     * @param sID user id
     */
    public static void enroll_1_Student(String cID, String sID){
        if(!doesCourseExist(cID))
            return;

        if(!doesStudentExist(sID))
            return;

        Boolean registered = false;

        for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
            List<ICourseOffering> enrollStuList = new ArrayList<>();
            List<StudentModel> enrollCourseList = new ArrayList<>();
            
            for(StudentModel student : course.getStudentsAllowedToEnroll()){
                if(course.getCourseID().equals(cID) && student.getID().equals(sID)){
                    if(course.getStudentsEnrolled().isEmpty()){
                        registered = true;


                        enrollStuList.add(course);
                        student.setCoursesEnrolled(enrollStuList);

                        enrollCourseList.add(student);
                        course.setStudentsEnrolled(enrollCourseList);

                        System.out.println("\nEnrolling " + student.getID() + " in " + course.getCourseID());
                    }
                    else{
                        registered = true;

                        enrollStuList.add(course);
                        student.setCoursesEnrolled(enrollStuList);

                        enrollCourseList.add(student);
                        course.setStudentsEnrolled(enrollCourseList);

                        System.out.println("\nEnrolling " + student.getID() + " in " + course.getCourseID());
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
    public static void setNotification(String courseName,String id){
        Scanner input = new Scanner(System.in);
        System.out.print("\n\tGive Notification Type (\"EMAIL\", \"PHONE\", \"MAIL\": ");
        String line = input.next();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseName);
        for (StudentModel student : course.getStudentsAllowedToEnroll()) {
            if(student.getID().equals(id)){
                switch (line.toUpperCase()){
                    case "EMAIL": student.setNotificationType(NotificationTypes.EMAIL);
                        System.out.println("\nYou should receive emails from now on, unless it gets lost.");
                        break;
                    case "PHONE": student.setNotificationType(NotificationTypes.CELLPHONE);
                        System.out.println("\nRing ring.");
                        break;
                    case "PIGEON": student.setNotificationType(NotificationTypes.PIGEON_POST);
                        System.out.println("\n1957 called. Pigeon's are no longer used.");
                        break;
                    case "MAIL": student.setNotificationType(NotificationTypes.MAIL);
                        System.out.println("\nWe are licking the stamps. Expect slobbery mail.");
                        break;
                    default:
                        System.out.println("\nInvalid selection. Process aborted and returning to main.");

                }
            }
        }
    }

    public static void addStudentMark(){
        Scanner input = new Scanner(System.in);
        Marking submitMark = new Marking();

        System.out.print("\n\t\t::: Mark Management :::\n\n\tEnter Student ID: ");
        String sID = input.next();

        System.out.print("\tEnter Course ID: ");
        String cID = input.next();

        System.out.print("\t** Following is case sensitive **\n\t "+
                "Enter type as 'Final', 'Midterm', or 'ASSIGNMENT-X': ");
        String typ = input.next();

        System.out.print("\tEnter grade received: ");
        Double gra = input.nextDouble();

        submitMark.addMark(cID, sID, typ, gra);




    }

    public static void createCourses(){
        BuildCourses newCourse = new BuildCourses();


    }

}


