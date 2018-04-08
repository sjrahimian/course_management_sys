import customDatatypes.Marks;
import customDatatypes.NotificationTypes;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import offerings.OfferingFactory;
import registrar.ModelRegister;
import systemUsers.StudentModel;

import java.io.*;
import java.util.*;

public class Operations {
    List<ICourseOffering> enrollStuList = new ArrayList<>();
    List<StudentModel> enrollCourseList = new ArrayList<>();

    /**
     * helper for checking courses
     * @param cID course to find
     */
    public Boolean doesCourseExist(String cID){
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
    public Boolean doesStudentExist(String stuID){
        StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(stuID);
        if(student != null){ //if there is such a course
            return true;
        }

        System.out.println("\nNo such student.");
        return false;

    }

    public void loadCourses() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("\n\tGive filename: ");
//            String line = reader.readLine();
//            String[] lineSplit = line.split(" ");

//            for (int i = 0; i <= lineSplit.length; i++) {
                buildCourseOffering("note_1.txt");
                buildCourseOffering("note_2.txt");
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "exception thrown at createCourses");
        }

    }

    /**
     * helper function that builds the courses
     * @param file filename given by user
     * @throws IOException
     */
    private void buildCourseOffering(String file) throws IOException {
        OfferingFactory factory = new OfferingFactory();
        BufferedReader br = new BufferedReader(new FileReader(new File(file)));
        CourseOffering courseOffering = factory.createCourseOffering(br);
        br.close();
    }

    /**
     * Print class record.
     * @param courseName name of course user wants
     * @param id user's ID
     */
    public void printClassRecord(String courseName, String id) {
        Printer print = new Printer();
        if(doesCourseExist(courseName))
            print.classRecord(courseName,id);
    }

    /**
     * Print course record
     * @param courseName get the course id
     * @param sID user's id
     */
    public void printStudentCourse(String courseName, String sID){
        Printer print = new Printer();
        if(doesCourseExist(courseName))
            print.studentsCourses(courseName,sID);
    }

    public void printAllStudentsCourses(String sID){
        Printer print = new Printer();
        print.allStudentsCourses(sID);
    }

    /**
     * Enrolls a student in course: adds student to course list, and course to student list
     * @param cID course name
     * @param sID user id
     */
    public void enrollStudent(String cID, String sID){
        if(!doesCourseExist(cID))
            return;

        if(!doesStudentExist(sID))
            return;

        Boolean registered = false;

        for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
            for(StudentModel student : course.getStudentsAllowedToEnroll()){
                if(course.getCourseID().equals(cID) && student.getID().equals(sID)){
                    if(course.getStudentsEnrolled().isEmpty()){
                        registered = true;

                        this.enrollStuList = new ArrayList<>();
                        this.enrollCourseList = new ArrayList<>();

                        enrollStuList.add(course);
                        student.setCoursesEnrolled(enrollStuList);

                        enrollCourseList.add(student);
                        course.setStudentsEnrolled(enrollCourseList);

                        System.out.println("\nEnrolling " + student.getID() + " in " + course.getCourseID());
                    }
                    else{
                        registered = true;

                        this.enrollStuList.add(course);
                        student.setCoursesEnrolled(this.enrollStuList);

                        this.enrollCourseList.add(student);
                        course.setStudentsEnrolled(this.enrollCourseList);

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
    public void setNotification(String courseName,String id){
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

    public void yabbadabba(String courseID, String studentID){
        Marking marking = new Marking();





    }

    public void createCourses(){
        BuildACourse newCourse = new BuildACourse();


    }

}


