
import offerings.CourseOffering;
import offerings.ICourseOffering;
import systemUsers.StudentModel;
import java.util.*;

/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Interface between frontend and backend functions implementations
 *
 */


public class Enrollment {
    private List<ICourseOffering> enrollStuList = new ArrayList<>();
    private List<StudentModel> enrollCourseList = new ArrayList<>();

    /**
     * Enrolls a student in course: adds student to course list, and course to student list
     * @param course course name
     * @param studentID user id
     */
    public void enroll_Student(CourseOffering course, String studentID){

        StudentModel student = findStudent(course, studentID);
        if(student == null){
            System.out.println("\n>>Enrollment denied: " + studentID + "<<");

            return;
        }


        for(StudentModel stuList : course.getStudentsAllowedToEnroll()){
            if(stuList.getID().equals(studentID)){
                //there is no enrollment list
                if(course.getStudentsEnrolled().isEmpty()){

                    this.enrollStuList = new ArrayList<>();
                    this.enrollCourseList = new ArrayList<>();

                    this.enrollStuList.add(course);
                    stuList.setCoursesEnrolled(enrollStuList);

                    this.enrollCourseList.add(stuList);
                    course.setStudentsEnrolled(enrollCourseList);

                    System.out.println("\nEnrolling " + stuList.getID() + " in " + course.getCourseID());
                }
                //there already is an enrollment list
                else{

                    this.enrollStuList.add(course);
                    stuList.setCoursesEnrolled(this.enrollStuList);

                    this.enrollCourseList.add(stuList);
                    course.setStudentsEnrolled(this.enrollCourseList);

                    System.out.println("\nEnrolling " + stuList.getID() + " in " + course.getCourseID());
                }
            }
        }



    }

    /**
     * find and return if student is allowed to enroll
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



}
