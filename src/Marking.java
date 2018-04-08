/**
 * @author Moe Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Houses functions for all your marking needs.
 *
 */

import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.ModelRegister;
import customDatatypes.Marks;
import systemUsers.StudentModel;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;


public class Marking {

    public Marking(){
    }

    public void addMark(String cID, String sID, String type, Double grade){
        Scanner input = new Scanner(System.in);
        Map<ICourseOffering,Marks> marksPackage = new Hashtable<>();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(cID);
        StudentModel student = findStudent(course, sID);
        if(student == null){
            System.out.println("No such student.");
            return;
        }

        Marks merks = new Marks();
        merks.addToEvalStrategy(type,grade);

        for(;;){
            System.out.print("\tDo you want to add more grades for student (" + sID + ")? Y/N: ");
            String ans = input.next();
            if(ans.toUpperCase().equals("No.") | ans.toUpperCase().equals("N")){
                break;
            }
            System.out.print("\tEnter type ('Final' or 'ASSIGNMENT-1'): ");
            String t = input.next();
            System.out.print("\tEnter grade: ");
            Double g = input.nextDouble();

            merks.addToEvalStrategy(t,g);

        }

        marksPackage.put(course, merks);
        student.setPerCourseMarks(marksPackage);

        Marks m = new Marks();
        m.addToEvalStrategy("Final",2.22);

    }

    public StudentModel findStudent(CourseOffering c, String id){
        for (StudentModel s : c.getStudentsEnrolled()){
            if(s.getID().equals(id))
                return s;
        }

        return null;
    }

}
