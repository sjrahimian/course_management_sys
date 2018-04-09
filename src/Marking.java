/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
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
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;


public class Marking {

    public Marking(){
    }

    public void addMark(String cID, String sID, String type, Double grade){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(cID);
        StudentModel student = findStudent(course, sID);
        if(student == null){
            System.out.println("No such student.");
            return;
        }

        Map<ICourseOffering,Marks> marksPackage = new Hashtable<>();

        Marks merks = new Marks();
        merks.addToEvalStrategy(type,grade);

        marksPackage.put(course, merks);
        student.setPerCourseMarks(marksPackage);

    }



    public StudentModel findStudent(CourseOffering c, String id){
        for (StudentModel s : c.getStudentsEnrolled()){
            if(s.getID().equals(id))
                return s;
        }

        return null;
    }

}
