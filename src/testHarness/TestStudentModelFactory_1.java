package testHarness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import customDatatypes.Marks;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import offerings.OfferingFactory;
import registrar.ModelRegister;
import systemUsers.StudentModel;

public class TestStudentModelFactory_1 {

    public static void main(String[] args) throws IOException {
        //		Create an instance of an OfferingFactory
        OfferingFactory factory = new OfferingFactory();

        //		Use the factory to populate as many instances of courses as many files we've got.
        BufferedReader br = new BufferedReader(new FileReader(new File("../note_1.txt")));
        @SuppressWarnings("unused")
		CourseOffering courseOffering = factory.createCourseOffering(br);
        br.close();

        //		Loading 1 file at a time
        br = new BufferedReader(new FileReader(new File("../note_2.txt")));
        //		here we have only two files
        courseOffering = factory.createCourseOffering(br);
        br.close();

        //		code to perform sanity checking of all our code
        //		by printing all of the data that we've loaded
        for (CourseOffering course: ModelRegister.getInstance().getAllCourses()) {
            System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " + course.getSemester());
            for (StudentModel student: course.getStudentsAllowedToEnroll()) {

                if (student.getID().equals("1264")) {
                    if (course.getCourseID().equals("CS2213A")) {

                        Marks m1 = new Marks();
                        m1.addToEvalStrategy("Final", 0.80);
                        m1.addToEvalStrategy("Midterm", 0.90);
                        m1.addToEvalStrategy("ASSIGNMENT-1", 0.95);
                        m1.addToEvalStrategy("ASSIGNMENT-2", 0.85);
                        

                        Map < ICourseOffering, Marks > markPackage = new Hashtable < > ();
                        markPackage.put(course, m1);

                        student.setPerCourseMarks(markPackage);


                        System.out.println("#######");
                        course.calculateFinalGrades();
                        System.out.println("#######");


                        if (student.getPerCourseMarks() != null) {
                            System.out.println("Course Marks:");
                            Marks bundle = student.getPerCourseMarks().get(course);

                            bundle.initializeIterator();
                            while (bundle.hasNext()) {
                                Map.Entry < String, Double > mark = bundle.getNextEntry();
                                System.out.println(String.format("%1$-15s %2$.2f", mark.getKey(), mark.getValue()));
                            }
                            course.calculateFinalGrade(student.getID());
                            course.calculateFinalGrades();
                        } else
                            System.out.println("\t\tNo marks");


                    }



                }

                System.out.println("--------------");
            }
        }

    }

}