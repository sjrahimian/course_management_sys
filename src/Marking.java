import offerings.CourseOffering;
import registrar.ModelRegister;
import customDatatypes.Marks;


import java.util.Map;

public class Marking {

    public Marking(){
    }

    public void newMarkinmarkinghahah(String courseID){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);

        Marks m = new Marks();
        m.addToEvalStrategy("Final",2.22);


    }



}
