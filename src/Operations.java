/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Interface between frontend and backend functions implementations
 *
 */

import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.NotificationTypes;
import customDatatypes.Weights;
import offerings.CourseOffering;
import registrar.ModelRegister;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;
import java.util.*;

public class Operations {
    private Enrollment enrollMe = new Enrollment();
    /**
     * helper for checking courses
     * @param cID course to find
     */
    public static Boolean doesCourseExist(String cID){
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(cID);
        if(course != null){ //if there is such a course
            return true;
        }

        System.out.println("\nNo such course.");
        return false;

    }

    /**
     * helper for checking courses
     * @param stuID course to find
     */
    public static Boolean doesSoAndSoExist(String stuID){
        SystemUserModel user = ModelRegister.getInstance().getRegisteredUser(stuID);
        if(user != null){ //if there is such a course
            return true;
        }

        System.out.println("\nNo such user id: " + stuID);
        return false;

    }

    /**
     * finds a student and returns them
     * @param c course
     * @param studentID student id
     * @return
     */
    public static StudentModel findStudent(CourseOffering c, String studentID){
        for (StudentModel s : c.getStudentsAllowedToEnroll()){
            if(s.getID().equals(studentID))
                return s;
        }

        return null;
    }

    /**
     * Load course registration files.
     */
    public static void loadCourses() {
        BuildCourses newCourse = new BuildCourses();
        newCourse.runRegistration();

    }

    /**
     * Print class record.
     * @param courseID name of course user wants
     * @param instructorID user's ID
     */
    public static void printRoster(String courseID, String instructorID) {
        Printer print = new Printer();
        print.classRecord(courseID,instructorID);
    }

    /**
     * Print course record for one student
     */
    public static void printStudentCourse(String studentID){
        Scanner input = new Scanner(System.in);

        if(!doesSoAndSoExist(studentID))
            return;

        System.out.print("\n\tGive course name (e.g., \"CS2212B\"): ");
        String courseID = input.next();
        courseID = courseID.toUpperCase();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseID);
        if(course == null){
            System.out.println("\nNo such course: " + courseID);
            return;
        }

        Printer print = new Printer();
        print.singleStudentsCourse(course,studentID);
    }

    /**
     * prints all courses that a student has been registered in
     * @param studentID student id
     */
    public static void printAllStudentsCourses(String studentID){
        Printer print = new Printer();
        print.allStudentsCourses(studentID);
    }

    /**
     * enrolls a new student
     */
    public static void enrollStudent(){
        Scanner input = new Scanner(System.in);

        System.out.print("\n\tCourse (e.g., \"CS2212B\") to enroll in: ");
        String c = input.next();
        c = c.toUpperCase();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(c);
        if(course == null){
            System.out.println("\nNo such course: " + c);
            return;
        }

        System.out.print("\tStudent ID to be enrolled: ");
        String s = input.next();

        if(doesSoAndSoExist(s))
            this.enrollMe.enroll_Student(course,s);
    }

    public void enrollStudentRequest(String studentID){
        Scanner input = new Scanner(System.in);

        System.out.print("\n\tCourse (e.g., \"CS2212B\") to enroll in: ");
        String c = input.next();
        c = c.toUpperCase();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(c);
        if(course == null){
            System.out.println("\nNo such course: " + c);
            return;
        }

        this.enrollMe.enroll_Student(course,studentID);

    }

    /**Add notification preferences.
     *
     * @param courseName get the course id
     * @param id user's id
     */
    public static void setNotification(String courseName,String id){
        Scanner input = new Scanner(System.in);

        input.close();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(courseName);
        if(course == null){
            System.out.println("\nNo such course.");
            return;
        }

        StudentModel student = findStudent(course, id);
        if(student == null){
            System.out.println("\nNo such student.");
            return;
        }

        System.out.print("\tGive Notification Type (\"EMAIL\", \"PHONE\", \"MAIL\"): ");
        String line = input.next();
        switch (line.toUpperCase()){
            case "EMAIL": student.setNotificationType(NotificationTypes.EMAIL);
                System.out.println("\nYou should receive emails from now on, unless it gets lost.");
                break;
            case "PHONE": student.setNotificationType(NotificationTypes.CELLPHONE);
                System.out.println("\nRing ring.");
                break;
            case "PIGEON": student.setNotificationType(NotificationTypes.PIGEON_POST);
                System.out.println("\nHand delivered to your door by a man dressed as a giant pigeon.");
                break;
            case "MAIL": student.setNotificationType(NotificationTypes.MAIL);
                System.out.println("\nWe are licking the stamps. Expect slobbery mail.");
                break;
            default:
                System.out.println("\nInvalid selection. Process aborted and returning to main.");

        }

    }

    /**
     * allows user to add new mark for a student
     */
    public static void addStudentMark(){
        Scanner input = new Scanner(System.in);
        Marking submitMark = new Marking();

        System.out.print("\n\t\t::: Mark Management: Add :::\n\n\tEnter Student ID: ");
        String sID = input.next();

        System.out.print("\tEnter Course ID: ");
        String cID = input.next();

        System.out.print("\t** Following is case sensitive **\n\t"+
                "Enter type as 'Final', 'Midterm', or 'ASSIGNMENT-X': ");
        String typ = input.next();

        System.out.print("\tEnter grade received (Format as '0.0' in decimal, i.e. 94% would be 0.94): ");
        Double gra = input.nextDouble();

        submitMark.addMark(cID, sID, typ, gra);

        input.close();
    }

    /**
     * allows user to modify a previously entered mark
     */
    public static void modifyMark(){
        Scanner input = new Scanner(System.in);
        Marking m = new Marking();

        System.out.print("\n\t\t::: Mark Management: Modify :::\n\n\tEnter Student ID: ");
        String sID = input.next();

        System.out.print("\tEnter Course ID: ");
        String cID = input.next();

        System.out.print("\t** Following is case sensitive **\n\t"+
                "Enter type as 'Final', 'Midterm', or 'ASSIGNMENT-x': ");
        String typ = input.next();

        System.out.print("\tEnter revised grade in decimal, i.e. 94% would be 0.94): ");
        Double gra = input.nextDouble();

        m.updateMark(cID, sID, typ, gra);
        
        input.close();
    }
    
    public static void calculateGrade() {
        Scanner input = new Scanner(System.in);

        System.out.print("\n\t\t::: Calculate Grade :::\n\n\tEnter Student ID: ");
        String sID = input.next();

        System.out.print("\tEnter Course ID: ");
        String cID = input.next();
        
        input.close();
    	
    	CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(cID);
    	if (course == null) {
    		System.out.println("No such course");
    		return;
    	}
    	
    	StudentModel student = findStudent(course, sID);
    	if(student == null) {
    		System.out.println("Student does not exist");
    		return;
    	}
    	
    	List<StudentModel> students = course.getStudentsEnrolled();
    	if (!students.contains(student)) {
    		System.out.println("Student is not enrolled in this course");
    		return;
    	}
    	
		double finalGrade = 0D;
		Map<EvaluationTypes, Weights> evaluationStrategies = course.getEvaluationStrategies(); 
		EvaluationTypes evalEntities = student.getEvaluationEntities().get(course);
		Weights weights = evaluationStrategies.get(evalEntities);
		Marks marks  = student.getPerCourseMarks().get(course);
		weights.initializeIterator();
		while(weights.hasNext()){
			weights.next();
			finalGrade += weights.getCurrentValue() * marks.getValueWithKey(weights.getCurrentKey());
		}
		
        System.out.println(sID + "\t--> FINAL GRADE :: " + finalGrade);
    	
    }

}


