/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Houses functions for all printing needs...
 *
 */

import customDatatypes.Marks;
import offerings.CourseOffering;
import registrar.ModelRegister;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import java.util.Map;

public class Printer extends Operations{

    /**
     * Print class record.
     * @param courseID name of course user wants
     * @param intsID user's ID
     */
    protected void classRecord(String courseID, String intsID) {
        System.out.println();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);
        if(course == null){
            System.out.println("\nNo such course: " + courseID);
            return;
        }

        for(InstructorModel instructor : course.getInstructor()){
            //print only logged in profs class
            if(instructor.getID().equals(intsID)){
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

    }

    /**
     * Print record for specific course & student
     * @param courseID get the course id
     * @param sID user's id
     */
    protected void singleStudentsCourse(String courseID, String sID){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);
        if(course == null){
            System.out.println("\nNo such course: " + courseID);
            return;
        }
        String teachList = "";

        for (StudentModel student : course.getStudentsEnrolled()){

            //construct instructor list
            for(InstructorModel instructor : course.getInstructor()){
                teachList += "\nCourse Instructor: " + instructor.getName() + instructor.getSurname();
            }

            if(student.getID().equals(sID))
                System.out.println("\nCourse ID: " + course.getCourseID() +
                        "\nCourse Name: " + course.getCourseName() + course.getSemester() + teachList +
                        "\nStudent Name: " + student.getName() + " " + student.getSurname() +
                        "\nStudent ID: " + student.getID() +
                        "\nStudent EvaluationType: " + student.getEvaluationEntities().get(course));
            //print course marks
            if(student.getPerCourseMarks() != null) {
                System.out.println("Course Marks:");
                if (student.getPerCourseMarks() != null) {
                    Marks bundle = student.getPerCourseMarks().get(course);

                    bundle.initializeIterator();
                    while (bundle.hasNext()) {
                        Map.Entry<String, Double> mark = bundle.getNextEntry();
                        System.out.println(String.format("%1$-15s %2$.2f", mark.getKey(), mark.getValue()));
                    }
                }
                else
                    System.out.println("\t\tNo marks");
            }

        }

    }

    /**
     * prints all courses that a student is registered in
     * @param stuID student id
     */
    protected void allStudentsCourses(String stuID){
        String teachList = "";
        for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
            for (StudentModel student : course.getStudentsEnrolled()) {

                //construct instructor list for course
                for(InstructorModel instructor : course.getInstructor()){
                    teachList += "\nCourse Instructor: " + instructor.getName() + instructor.getSurname();
                }

                if(student.getID().equals(stuID))
                    System.out.println("\nCourse ID: " + course.getCourseID() +
                            "\nCourse Name: " + course.getCourseName() + course.getSemester() + teachList +
                            "\nStudent Name: " + student.getName() + " " + student.getSurname() +
                            "\nStudent ID: " + student.getID() +
                            "\nStudent EvaluationType: " + student.getEvaluationEntities().get(course));
                //print course marks
                if(student.getPerCourseMarks() != null) {
                    System.out.println("Course Marks:");
                    if (student.getPerCourseMarks() != null) {
                        Marks bundle = student.getPerCourseMarks().get(course);

                        bundle.initializeIterator();
                        while (bundle.hasNext()) {
                            Map.Entry<String, Double> mark = bundle.getNextEntry();
                            System.out.println(String.format("%1$-15s %2$.2f", mark.getKey(), mark.getValue()));
                        }
                    }
                    else
                        System.out.println("\t\tNo marks");
                }

            }
        }

    }


}
