/**
 * @author Moe Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Frontend that presents user end functions and accepts clients requests via command line style interface
 * ~~where the magic happens~~
 *
 */

import authenticatedUsers.*;
import authenticationServer.AuthenticationToken;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;


class CMS{
    private static Boolean sys_state = false;  //system is off
    private static Boolean sys_state_create = true;
    private Operations operations;
    private int counter;

    public void CMS(){
        this.counter = 0;
    }

    public void login() throws IOException {
        AuthenticationToken token;
        Authenticate auth = new Authenticate();
        Database db = new Database("loginDB.txt");
        Scanner input = new Scanner(System.in);
        String password, id;
        String[] userData;

        for (; ; ) {
            //try for user id
            System.out.print("User ID: ");
            id = input.next();

            //check for non-numeric characters
            if(!id.matches("[0-9]+")) {
            	System.out.println("Invalid ID: " + id + ". Try again.\n");
            }
            else if(!db.containsUser(Integer.parseInt(id))){
                System.out.println("No such user.\n");
            }
            else{   //user id in database
                userData = db.getAllData(Integer.parseInt(id)); //try for password
                System.out.print("Password: ");
                //disable input.next() --> for IDE testing, then enable following 3 lines for console testing
                password = input.next();
//                Console con = System.console();
//                char[] passString = con.readPassword();
//                password = new String(passString);

                try {
                	if(!password.matches("[0-9]+")) {
                    	System.out.println("Password invalid. Try again.\n");
                    }
                    else if (auth.checkPassword(auth.encode(password), userData[3])) {

                        //check system state
                        if(!userData[4].equals("Admin") && !sys_state){ //system state is offline
                            System.out.println("System is offline. Contact a System Administrator.\n");
                        }
                        else {  //system state is online or an Admin
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, d MMMM, YYYY HH:mm:ss z VV ");
                            ZonedDateTime now = ZonedDateTime.now();
                            String OS = System.getProperty("os.name");
                            System.out.println("\n\tWossamotta U: Course Management System (CMS)");
                            System.out.println("\tLogging in on " + OS + " on " + now.format(dtf) + "\n");

                            //generate whenever someone logs in and set methods
                            token = new AuthenticationToken();
                            token.setSessionID((int) System.currentTimeMillis());//session date and time
                            token.setTokenID(this.counter++);//increment token ID
                            token.setUserType(db.getUserType(Integer.parseInt(id)));//id will determine user type
                            break;
                        }
                    }
                    else {
                        System.out.println("password >>> " + auth.encode(password));

                        System.out.println("Incorrect password. Try again.\n");
                    }
                }
                catch (AuthenticateError authenticateError) {
                    authenticateError.printStackTrace();
                }
            }

        }


        //create new log session
        //switch statement after tokens
        switch(token.getUserType()){
            case "Admin":
                LoggedInAdmin admin = new LoggedInAdmin();
                admin.setupAdmin(token,userData);
                Administrator(admin);
                break;
            case "Instructor":
                LoggedInInstructor instructor = new LoggedInInstructor();
                instructor.setupInstructor(token,userData);
                Instructor(instructor);
                break;
            case "Student":
                LoggedInStudent student = new LoggedInStudent();
                student.setupStudent(token,userData);
                Student(student);
        }

    }

    private void logout(){System.out.println("Goodbye.");}

    public void Administrator(LoggedInAdmin admin){
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome Administrator " + admin.getName() + " " + admin.getSurname() + ". Select an option:");
        System.out.print("\t1. START System State (auto-activates option 3)" +
                "\n\t2. STOP System State" +
                "\n\t3. Create Courses" +
                "\n\t4. Enroll a student" +
                "\n\tType \"logout\" to leave\n\t\t$> ");
        String line = input.next();

        while((line.toLowerCase().equals("logout")) != true){

            switch(line){
                case "1":
                    sys_state = admin.modifySystemState(sys_state,1);
                    if(sys_state_create) {
                        sys_state_create = false;
                        operations = new Operations();
                        operations.loadCourses();
                    }
                    break;
                case "2": System.out.print("CAUTION!! ARE YOU SURE YOU WANT TO SHUTDOWN THE SYSTEM??\n\t\t'Yes' or 'no': ");
                    String choice = input.next();
                    if(choice.toUpperCase().equals("Y") || choice.toUpperCase().equals("YES"))
                        sys_state = admin.modifySystemState(sys_state,0);
                    else
                        System.out.println("Phew! That was close.");
//                    if(sys_state_create) {//                        sys_state_create = true;//                        operations = null;//                    }
                    break;
                case "3":if(!sys_state){System.out.println("Turn system on first.");break;}
                    operations.loadCourses();
                    break;
                case "4":if(!sys_state){System.out.println("Turn system on first.");break;}
                    System.out.print("\n\tCourse (e.g., \"CS2212B\") to enroll in: ");
                    String corID = input.next();
                    System.out.print("\tStudent ID to be enrolled: ");
                    String stuID = input.next();
                    operations.enroll_1_Student(corID.toUpperCase(),stuID);

                    break;
                default:
                    System.out.println("\nInvalid option.");
            }


            System.out.print("\n\nSelect an option:" +
                    "\n\t1. START System State (will also activate option 3)" +
                    "\n\t2. STOP System State" +
                    "\n\t3. Create Courses" +
                    "\n\t4. Enroll a student" +
                    "\n\tType \"logout\" to leave\n\t\t$> ");
            line = input.next();

        }

        logout();
        System.out.println();

    }

    public void Instructor(LoggedInInstructor instructor){
    	Scanner input = new Scanner(System.in);

        System.out.println("Welcome Instructor " + instructor.getName() + " " + instructor.getSurname() + ". Select an option:");
        System.out.print("\t1. Add mark for a student." +
                "\n\t2. Modify mark for a student." +
                "\n\t3. Calculate final grade." +
                "\n\t4. Print course roster." +
                "\n\t5. Print single student's course." +
                "\n\tType \"logout\" to leave\n\t\t$> ");
        String line = input.next();

        while((line.toLowerCase().equals("logout")) != true){

            switch(line){
                case "1": operations.addStudentMark();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4": System.out.print("\n\tGive course name (e.g., \"CS2212B\"): ");
                    String cou = input.next();
                    operations.printRoster(cou.toUpperCase(), instructor.getID());
                    break;
                case "5": System.out.print("\n\tGive course name (e.g., \"CS2212B\"): ");
                    String cName = input.next();
                    System.out.print("\tGive Student's ID: ");
                    operations.printStudentCourse(cName.toUpperCase(), input.next());
                    break;
                default:
                    System.out.println("\nInvalid option.");
            }


            System.out.print("\n\nSelect an option:" +
                    "\n\t1. Add mark for a student." +
                    "\n\t2. Modify mark for a student." +
                    "\n\t3. Calculate final grade." +
                    "\n\t4. Print course record." +
                    "\n\t5. Print single student's course." +
                    "\n\tType \"logout\" to leave\n\t\t$> ");
            line = input.next();

        }

        logout();
        System.out.println();

    }

    public void Student(LoggedInStudent student){
    	Scanner input = new Scanner(System.in);

        System.out.println("Welcome Student " + student.getName() + " " + student.getSurname() + ". Select an option:");
        System.out.print("\t1. Enroll in course." +
//                "\n\t2. Select notification status." +
                "\n\t2. Add notification preferences." +
                "\n\t3. Print course record." +
                "\n\t4. Print all course records." +
                "\n\tType \"logout\" to leave\n\t\t$> ");
        String line = input.next();

        while((line.toLowerCase().equals("logout")) != true){

            switch(line){
                case "1":
                    break;
                case "2": System.out.print("\n\tGive course name (e.g., \"CS2212B\") for notification change: ");
                    operations.setNotification(input.next().toUpperCase(), student.getID());
                    break;
                case "3": System.out.print("\n\tGive course name (e.g., \"CS2212B\"): ");
                    operations.printStudentCourse(input.next().toUpperCase(),student.getID());
                    break;
                case "4": operations.printAllStudentsCourses(student.getID());
                default:
                    System.out.println("\nInvalid option.");
            }


            System.out.print("\n\nSelect an option:" +
                    "\n\t1. Enroll in course." +
//                    "\n\t2. Select notification status." +
                    "\n\t2. Add notification preferences." +
                    "\n\t3. Print single course record." +
                    "\n\t4. Print all course records." +
                    "\n\tType \"logout\" to leave\n\t\t$> ");
            line = input.next();

        }

        logout();
        System.out.println();

    }


}


public class Main {
    public static void main(String[] args) throws IOException {
        CMS run = new CMS();
        for(;;){
            System.out.println("Wossamotta U: Course Management System (CMS)");
            System.out.println("\t\tCTRL-D to exit.\n");
            run.login();

        }

    }
}
