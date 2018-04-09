/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Houses function for building course by hand...why would anyone do that??.
 *
 */

import offerings.CourseOffering;
import offerings.OfferingFactory;
import java.io.*;

public class BuildCourses {
//    private CourseOffering newCourse;

    /**
     * Makes file requests to user, parses if more than one is provided, then creates courses
     */
    public void runRegistration() {
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("\n\tGive filename (separate multiple filenames with a space): ");
//            String line = reader.readLine();
//            String[] lineSplit = line.split(" ");

//            for (int i = 0; i <= lineSplit.length; i++) {
            buildCourseOffering("../note_1.txt");
            buildCourseOffering("../note_2.txt");
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "exception thrown. Error in course registration.");
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
        banner(courseOffering.getCourseName(), courseOffering.getCourseID());
        br.close();
    }

    private void banner(String name, String id){
        System.out.println("\n :: New course data " + name + ", " + id + " loaded ::");
    }


    //            this.newCourse.setSemester(Integer.parseInt(input));
    //        else
    //            System.out.println("Invalid, not a number.\n");
    //        if(!input.matches("[0-9]+"))
    //
    //        input = scan.next();
    //        System.out.print("\nEnter Semester: ");
    //
    //        String input;
    //        Scanner scan = new Scanner(System.in);
    //    private void semester(){
    //
    //    }
    //        this.newCourse.setCourseID(input.toUpperCase());
    //        input = scan.next();
    //        System.out.print("\nEnter Course ID: ");
    //
    //        String input;
    //        Scanner scan = new Scanner(System.in);
    //    private void courseID(){
    //
    //    }
    //        this.newCourse.setCourseName(input);
    //
    //        input = scan.next();
    //        System.out.print("\nEnter Course ID: ");
    //        String input;
    //        Scanner scan = new Scanner(System.in);
    //    private void courseName(){
    //
    //    }
    //
    //        this.newCourse.getInstructor().add(newInstructorModel);
    //
    //        newInstructorModel.getIsTutorOf().add(this.newCourse);
    //        newInstructorModel = (InstructorModel) ModelRegister.getInstance().getRegisteredUser(input);
    //        }
    //            ModelRegister.getInstance().registerUser(newInstructorModel.getID(), newInstructorModel);
    //            newInstructorModel.setIsTutorOf(new ArrayList<ICourseOffering>());
    //
    //            input = scan.next();
    //            System.out.print("\nEnter Course ID: ");
    //            newInstructorModel.setID(input);
    //
    //            input = scan.next();
    //            System.out.print("\nEnter Course ID: ");
    //
    //            newInstructorModel.setSurname(input);
    //            input = scan.next();
    //            System.out.print("\nEnter Course ID: ");
    //
    //            newInstructorModel.setName(input);
    //            input = scan.next();
    //            System.out.print("\nEnter Course ID: ");
    //        if(!ModelRegister.getInstance().checkIfUserHasAlreadyBeenCreated(input)){
    //
    //        input = scan.next();
    //        System.out.print("\nEnter Course ID: ");
    //        String input;
    //        Scanner scan = new Scanner(System.in);
    //
    //        InstructorModel newInstructorModel = new InstructorModel();
    //    private void instructor(){
    //
    //    }
    //        this.newCourse.setEvaluationStrategies(new HashMap<EvaluationTypes, Weights>());
    //
    //    private void EvalStart(){
    //
    //    }
    //
    //        this.newCourse.setStudentsAllowedToEnroll(new ArrayList<StudentModel>());
    //
    //    private void StudentsAllowed(){
    //
    //    }
    //
    //        this.newCourse.setStudentsEnrolled(new ArrayList<StudentModel>());
    //
    //    private void studentEnrolled(){
    //
    //    }
    //
    //        ModelRegister.getInstance().registerCourse(this.newCourse.getCourseID(), this.newCourse);
    //
    //        semester();
    //
    //        instructor();
    //        courseName();
    //        courseID();
    //        banner();
    //
    //        this.newCourse = new CourseOffering();
//    public BuildCourses(){

//    }


}
