
package testHarness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import offerings.OfferingFactory;
import registrar.ModelRegister;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;

public class TestStudentModelFactory_1 {

//	public static void main(String[] args) throws IOException{
//		// TODO Auto-generated method stub
//		SystemUserModelFactory factory = new StudentModelFactory();
//		BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
//		factory.createSystemUserModel(br, null);
//	}

	public static void main(String[] args) throws IOException{
//		Create an instance of an OfferingFactory
		OfferingFactory factory = new OfferingFactory();

//		Use the factory to populate as many instances of courses as many files we've got.
        BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
        CourseOffering	courseOffering = factory.createCourseOffering(br);
		br.close();

//		Loading 1 file at a time
		br = new BufferedReader(new FileReader(new File("note_2.txt")));
//		here we have only two files
		courseOffering = factory.createCourseOffering(br);
		br.close();

//		code to perform sanity checking of all our code
//		by printing all of the data that we've loaded
		for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
			System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
			course.getSemester());
//			System.out.println("Students allowed to enroll\n");
			for(StudentModel student : course.getStudentsAllowedToEnroll()){
//				System.out.println("Student name : " + student.getName() + "\nStudent surname : " + student.getSurname() +
//						"\nStudent ID : " + student.getID() + "\nStudent EvaluationType : " +
//						student.getEvaluationEntities().get(course) + "\n\n");
                if(student.getID().equals("1264")){
                    if(course.getCourseID().equals("CS2212B")){
                        Marks m1 = new Marks();
                        m1.addToEvalStrategy("Final",22.22);
                        m1.addToEvalStrategy("Midterm", 88.2);

                        Map<ICourseOffering,Marks> markPackage = new Hashtable<>();
                        markPackage.put(course,m1);

                        student.setPerCourseMarks(markPackage);
                    }



                    System.out.println("#######");
                    course.calculateFinalGrades();
                    System.out.println("#######");

//                    course.calculateFinalGrade(student.getID());

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


//			for(StudentModel student : course.getStudentsAllowedToEnroll()){
//				for(ICourseOffering course2 : student.getCoursesAllowed())
//				System.out.println(student.getName() + "\t\t -> " + course2.getCourseName());
//			}
            System.out.println("--------------");
        }


    }
}
