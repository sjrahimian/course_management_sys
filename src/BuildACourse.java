import customDatatypes.EvaluationTypes;
import customDatatypes.Weights;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.ModelRegister;
import systemUserModelFactories.InstructorModelFactory;
import systemUserModelFactories.SystemUserModelFactory;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;

import java.util.*;

/**
 * @author Moe Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Houses function for building course by hand...why would anyone do that??.
 *
 */


public class BuildACourse {
    private CourseOffering newCourse;


    public BuildACourse(){
        this.newCourse = new CourseOffering();

        banner();
        courseID();
        courseName();
        instructor();

        semester();

        ModelRegister.getInstance().registerCourse(this.newCourse.getCourseID(), this.newCourse);

    }

    private void studentEnrolled(){

        this.newCourse.setStudentsEnrolled(new ArrayList<StudentModel>());

    }

    private void StudentsAllowed(){

        this.newCourse.setStudentsAllowedToEnroll(new ArrayList<StudentModel>());

    }

    private void EvalStart(){

        this.newCourse.setEvaluationStrategies(new HashMap<EvaluationTypes, Weights>());
    }

    private void instructor(){
        InstructorModel newInstructorModel = new InstructorModel();

        Scanner scan = new Scanner(System.in);
        String input;
        System.out.print("\nEnter Course ID: ");
        input = scan.next();

        if(!ModelRegister.getInstance().checkIfUserHasAlreadyBeenCreated(input)){
            System.out.print("\nEnter Course ID: ");
            input = scan.next();
            newInstructorModel.setName(input);

            System.out.print("\nEnter Course ID: ");
            input = scan.next();
            newInstructorModel.setSurname(input);

            System.out.print("\nEnter Course ID: ");
            input = scan.next();

            newInstructorModel.setID(input);
            System.out.print("\nEnter Course ID: ");
            input = scan.next();

            newInstructorModel.setIsTutorOf(new ArrayList<ICourseOffering>());
            ModelRegister.getInstance().registerUser(newInstructorModel.getID(), newInstructorModel);
        }
        newInstructorModel = (InstructorModel) ModelRegister.getInstance().getRegisteredUser(input);
        newInstructorModel.getIsTutorOf().add(this.newCourse);

        this.newCourse.getInstructor().add(newInstructorModel);

    }

    private void courseName(){
        Scanner scan = new Scanner(System.in);
        String input;
        System.out.print("\nEnter Course ID: ");
        input = scan.next();

        this.newCourse.setCourseName(input);
    }

    private void courseID(){
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.print("\nEnter Course ID: ");
        input = scan.next();
        this.newCourse.setCourseID(input.toUpperCase());
    }

    private void semester(){
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.print("\nEnter Semester: ");
        input = scan.next();

        if(!input.matches("[0-9]+"))
            System.out.println("Invalid, not a number.\n");
        else
            this.newCourse.setSemester(Integer.parseInt(input));
    }

    private void banner(){
        System.out.println("\n :: New Course Register ::");
    }




}
