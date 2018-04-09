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
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\n\tGive filename (separate multiple filenames with a comma): ");
            String line = reader.readLine();
            String[] lineSplit = line.split(",");

            for (int i = 0; i < lineSplit.length; i++) {
//            buildCourseOffering("../note_1.txt");
            	buildCourseOffering(lineSplit[i]);
            }
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
        System.out.println(":: New course data " + name + ", " + id + " loaded ::");
    }


}
