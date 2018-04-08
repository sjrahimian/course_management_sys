import customDatatypes.Marks;
import customDatatypes.NotificationTypes;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import offerings.OfferingFactory;
import registrar.ModelRegister;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;

import java.io.*;
import java.util.*;

public class Operations {
    private List<CourseOffering> coursesOffered;
    List<ICourseOffering> newStu = new ArrayList<>();
    List<StudentModel> newCour = new ArrayList<>();

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
        Boolean found = false;
        System.out.println();
        for (CourseOffering course : ModelRegister.getInstance().getAllCourses()) {
            //if course is requested;
            if(course.getCourseID().equals(courseName)) {
                found = true;
                for(InstructorModel instructor : course.getInstructor()){
                    //print only logged in profs class
                    if(instructor.getID().equals(id)){
                        System.out.println("Course ID: " + course.getCourseID() +
                                "\nCourse name: " + course.getCourseName() +
                                "\nSemester: " + course.getSemester());

                        for(InstructorModel teach : course.getInstructor()) {
                            System.out.println("Instructor: " + teach.getName() + " " + teach.getSurname() +
                                    "\nInstructor ID: " + teach.getID());
                        }


                        System.out.println("\nStudent List:");
                        if(!course.getStudentsEnrolled().isEmpty()){
                            for (StudentModel student : course.getStudentsEnrolled()) {

                                        System.out.println("COURSE COURSE COURSE" + course.getCourseID()
                                        + "     list" + course.getStudentsEnrolled());
                                        System.out.println("Student name: " + student.getName() + " " + student.getSurname() +
                                                "\nStudent ID: " + student.getID() +
                                                "\nStudent EvaluationType: " + student.getEvaluationEntities().get(course) + "\n\n");



                            }
                        }
                        else{
                            System.out.println("\t\tNo students enrolled.");
                        }

                    }
                }
                break;
            }

        }
        if(!found){
            System.out.println("\t\t"+ courseName + " is not in the database.");
        }

    }

    public void enrollStudent(String cID, String sID){

        for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
            for(StudentModel student : course.getStudentsAllowedToEnroll()){
                if(course.getCourseID().equals(cID) && student.getID().equals(sID)){
                    if(course.getStudentsEnrolled().isEmpty()){
                        this.newStu = new ArrayList<>();
                        this.newCour = new ArrayList<>();

                        System.out.print(cID + "====" + course.getCourseID());
                        System.out.print(sID + "====" + student.getID());
                        newStu.add(course);
                        student.setCoursesEnrolled(newStu);

                        newCour.add(student); ///there is a bug here; adding the same student twice.
                        course.setStudentsEnrolled(newCour);

                        System.out.println("Enrolling " + student.getID() + " in " + course.getCourseID());
                    }
                    else{ System.out.println("This should print when doing cs2213a");
                        this.newStu = student.getCoursesEnrolled();
                        this.newCour = course.getStudentsEnrolled();

                        System.out.print(cID + "====" + course.getCourseID());
                        System.out.print(sID + "====" + student.getID());

                        this.newStu.add(course);
                        student.setCoursesEnrolled(this.newStu);

                        newCour.add(student);
                        course.setStudentsEnrolled(newCour);

                        System.out.println("Enrolling " + student.getID() + " in " + course.getCourseID());
                    }

                }
            }
            System.out.println("111111--------------");
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

    /**
     * Print course record
     * @param courseName get the course id
     * @param sID user's id
     */
    public void printStudentCourse(String courseName, String sID){

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseName);
        for (StudentModel student : course.getStudentsAllowedToEnroll()) {
            if(student.getID().equals(sID))
                System.out.println("\nCourse ID: " + course.getCourseID() +
                        "\nCourse Name: " + course.getCourseName() + course.getSemester() +
                        "\nCourse Instructor: " + course.getInstructor() +
                        "\nStudent Name: " + student.getName() + " " + student.getSurname() +
                        "\nStudent ID: " + student.getID() +
                        "\nStudent EvaluationType: " + student.getEvaluationEntities().get(course));
            if(student.getPerCourseMarks() != null)
                System.out.println("Course Marks: " + student.getPerCourseMarks().get(course));
        }
    }

    public void addGrade(String id){

    }

    public void createCourses(){
        BuildACourse newCourse = new BuildACourse();


    }

}


