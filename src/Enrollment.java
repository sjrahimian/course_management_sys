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

        Boolean registered = false;

        for(StudentModel stuList : course.getStudentsAllowedToEnroll()){
            if(stuList.getID().equals(studentID)){
                //there is no enrollment list
                if(course.getStudentsEnrolled().isEmpty()){
                    registered = true;

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
                    registered = true;

                    this.enrollStuList.add(course);
                    stuList.setCoursesEnrolled(this.enrollStuList);

                    this.enrollCourseList.add(stuList);
                    course.setStudentsEnrolled(this.enrollCourseList);

                    System.out.println("\nEnrolling " + stuList.getID() + " in " + course.getCourseID());
                }
            }
        }

        //did we register anyone this time?
        if(!registered){
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



    /* old enroll backup */
//    /**
//     * Enrolls a student in course: adds student to course list, and course to student list
//     * @param courseID course name
//     * @param studentID user id
//     */
//    public void enroll_1_Student(String courseID, String studentID){
//        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);
//        if(course == null){
//            System.out.println("\nNo such course: " + courseID);
//            return;
//        }
//
//        StudentModel student = findStudent(course, studentID);
//        if(student == null){
//            System.out.println("\nNo such student: " + studentID);
//            return;
//        }
//
//        Boolean registered = false;
//
//        for(CourseOffering course2 : ModelRegister.getInstance().getAllCourses()){
//            for(StudentModel student2 : course2.getStudentsAllowedToEnroll()){
//                if(course2.getCourseID().equals(courseID) && student2.getID().equals(studentID)){
//                    if(course2.getStudentsEnrolled().isEmpty()){
//                        registered = true;
//
//                        this.enrollStuList = new ArrayList<>();
//                        this.enrollCourseList = new ArrayList<>();
//
//                        enrollStuList.add(course2);
//                        student2.setCoursesEnrolled(enrollStuList);
//
//                        enrollCourseList.add(student2);
//                        course2.setStudentsEnrolled(enrollCourseList);
//
//                        System.out.println("\nEnrolling " + student2.getID() + " in " + course2.getCourseID());
//                    }
//                    else{
//                        registered = true;
//
//                        this.enrollStuList.add(course2);
//                        student2.setCoursesEnrolled(this.enrollStuList);
//
//                        this.enrollCourseList.add(student2);
//                        course2.setStudentsEnrolled(this.enrollCourseList);
//
//                        System.out.println("\nEnrolling " + student2.getID() + " in " + course2.getCourseID());
//                    }
//                }
//            }
//        }
//
//        if(!registered){
//            System.out.println("\n>>Denied enrollment<<");
//        }
//
//
//    }


}
