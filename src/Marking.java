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
import java.util.Map;


public class Marking {

    public Marking(){
    }

    public void newMarkinmarkinghahaha(String cID, String sID, String type, Double grade){
        Map<ICourseOffering,Marks> markPackage = new HashMap<>();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(cID);
        for(StudentModel s : course.getStudentsEnrolled()){
            if(s.getID().equals(sID)){
                Marks merks = new Marks();
                merks.addToEvalStrategy(type,grade);
                markPackage.put(course, merks);

                s.setPerCourseMarks(markPackage);

            }
        }

        Marks m = new Marks();
        m.addToEvalStrategy("Final",2.22);

    }



}
