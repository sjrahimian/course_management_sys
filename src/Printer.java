///**
// * @author Moe Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
// * @version 0.1
// * Winter cs2212
// *
// * Houses functions for all printing needs...
// *
// */
//
//import offerings.CourseOffering;
//import registrar.ModelRegister;
//import systemUsers.InstructorModel;
//import systemUsers.StudentModel;
//
//public class Printer extends Operations{
//
//    /**
//     * Print class record.
//     * @param courseName name of course user wants
//     * @param id user's ID
//     */
//    public void classRecord(String courseName, String id, ModelRegister reg) {
//        System.out.println();
//        for (CourseOffering course : reg.getAllCourses()) {
//            //if course is requested;
//            if(course.getCourseID().equals(courseName)) {
//                for(InstructorModel instructor : course.getInstructor()){
//                    //print only logged in profs class
//                    if(instructor.getID().equals(id)){
//                        System.out.println("Course ID: " + course.getCourseID() +
//                                "\nCourse name: " + course.getCourseName() +
//                                "\nSemester: " + course.getSemester());
//
//                        for(InstructorModel teach : course.getInstructor()) {
//                            System.out.println("Instructor: " + teach.getName() + " " + teach.getSurname() +
//                                    "\nInstructor ID: " + teach.getID());
//                        }
//
//
//                        System.out.println("\nStudent List:");
//                        if(!course.getStudentsEnrolled().isEmpty()){
//                            for (StudentModel student : course.getStudentsEnrolled()) {
//                                System.out.println("Student name: " + student.getName() + " " + student.getSurname() +
//                                        "\nStudent ID: " + student.getID() +
//                                        "\nStudent EvaluationType: " + student.getEvaluationEntities().get(course) + "\n\n");
//                            }
//                        }
//                        else{
//                            System.out.println("\t\t No students enrolled.");
//                        }
//
//                    }
//                    else{
//                        System.out.println("\t\tAccess denied to: "+ courseName + ".");
//                        break;
//                    }
//
//                }
//            }
//            else{
//                System.out.println("\t\tNo such course: "+ courseName);
//                break;
//            }
//
//
//        }
//
//    }
//
//
//    /**
//     * Print course record
//     * @param courseName get the course id
//     * @param sID user's id
//     */
//    public void studentCourse(String courseName, String sID, ModelRegister reg){
//
//        CourseOffering course = reg.getRegisteredCourse(courseName);
//        for (StudentModel student : course.getStudentsAllowedToEnroll()) {
//            if(student.getID().equals(sID))
//                System.out.println("\nCourse ID: " + course.getCourseID() +
//                        "\nCourse Name: " + course.getCourseName() + course.getSemester() +
//                        "\nCourse Instructor: " + course.getInstructor() +
//                        "\nStudent Name: " + student.getName() + " " + student.getSurname() +
//                        "\nStudent ID: " + student.getID() +
//                        "\nStudent EvaluationType: " + student.getEvaluationEntities().get(course) );
//            //+ "\nCourse Marks: " + student.getPerCourseMarks().get(course));
//        }
//    }
//
//}
