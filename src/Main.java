import authenticatedUsers.*;
import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

class CMS{
    private static Boolean sys_state = false;  //system is off
    private static Boolean sys_state_create = true;
    private int counter;

    public void CMS(){
        this.counter = 0;
    }

    public void login() throws IOException {
        AuthenticationToken token;
        Authenticate auth = new Authenticate();
        Database db = new Database("userDB.txt");
        Scanner input = new Scanner(System.in);
        String password, id;
        String[] possibleUser;

        for (; ; ) {
            //try for user id
            System.out.print("User ID: ");
            id = input.next();

            if(!db.containsUser(Integer.parseInt(id))){
                System.out.println("No such user. Try again.\n");
            }
            else{   //user id in database
                possibleUser = db.getAllData(Integer.parseInt(id));
                //try for password
                System.out.print("Password: ");
                //disable input.next() --> for IDE testing, then enable following 3 lines for console testing
                password = input.next();
//                Console con = System.console();
//                char[] passString = con.readPassword();
//                password = new String(passString);

                try {
                    if (auth.checkPassword(auth.encode(password), possibleUser[3])) {

                        //check system state
                        if(!possibleUser[4].equals("Admin") && !sys_state){ //system state is offline
                            System.out.println("System is offline. Contact a System Administrator.\n");
                        }
                        else {  //system state is online or an Admin
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, d MMMM, YYYY HH:mm:ss z VV ");
                            ZonedDateTime now = ZonedDateTime.now();
                            String OS = System.getProperty("os.name");
                            System.out.println("\n\tCollege of Oarsmanship: Course Management System (CMS)");
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
                Administrator(token,possibleUser);
                break;
            case "Instructor":
                Instructor(token,possibleUser);
                break;
            case "Student":
                Student(token,possibleUser);
        }

    }

    private void logout(){}

    public void Administrator(AuthenticationToken tk, String[] user){
        Scanner input = new Scanner(System.in);
        LoggedInUserFactory log = new LoggedInUserFactory();
        LoggedInAdmin admin;
        admin = (LoggedInAdmin) log.createAuthenticatedUser(tk);

        System.out.println("Welcome Administrator " + user[0] + " " + user[1] + ". Select an option:");
        System.out.print("\t1. START System State (auto-activates option 3)" +
                "\n\t2. STOP System State" +
                "\n\t3. Create Courses" +
                "\n\tType \"logout\" to leave\n\t\t$> ");
        String line = input.next();

        while((line.equals("logout")) != true){

            switch(line){
                case "1":
                    sys_state = admin.modifySystemState(sys_state,1);
                    if(sys_state_create) {
                        sys_state_create = false;
                        admin.createCourses();
                    }
                    break;
                case "2":
                    sys_state = admin.modifySystemState(sys_state,0);
                    break;
                case "3":
                    admin.createCourses();
                    break;
                default:
                    System.out.println("\nInvalid option.");
            }


            System.out.print("\nSelect an option:" +
                    "\n\t1. START System State (will also activate option 3)" +
                    "\n\t2. STOP System State" +
                    "\n\t3. Create Courses" +
                    "\n\tType \"logout\" to leave\n\t\t$> ");
            line = input.next();

        }

        logout();
        System.out.println();

    }

    public void Instructor(AuthenticationToken tk, String[] user){
        LoggedInUserFactory log = new LoggedInUserFactory();
        LoggedInInstructor instructor;

        instructor = (LoggedInInstructor) log.createAuthenticatedUser(tk);
        System.out.println("Welcome Instructor " + instructor.getName() +".");

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Administrator " + user[0] + " " + user[1] + ". Select an option:");
        System.out.print("\t1. ------------------" +
                "\n\t2. ------------------" +
                "\n\t3. ------------------" +
                "\n\tType \"logout\" to leave\n\t\t$> ");
        String line = input.next();

        while((line.equals("logout")) != true){

            switch(line){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("\nInvalid option.");
            }


            System.out.print("\nSelect an option:" +
                    "\n\t1. ------------------" +
                    "\n\t2. ------------------" +
                    "\n\t3. ------------------" +
                    "\n\tType \"logout\" to leave\n\t\t$> ");
            line = input.next();

        }

        logout();
        System.out.println();

    }

    public void Student(AuthenticationToken tk, String[] user){
        LoggedInUserFactory log = new LoggedInUserFactory();
        LoggedInStudent student;

        student = (LoggedInStudent) log.createAuthenticatedUser(tk);
        System.out.println("User: " + student.getName() + student.getSurname());

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Administrator " + user[0] + " " + user[1] + ". Select an option:");
        System.out.print("\t1. ------------------" +
                "\n\t2. ------------------" +
                "\n\t3. ------------------" +
                "\n\tType \"logout\" to leave\n\t\t$> ");
        String line = input.next();

        while((line.equals("logout")) != true){

            switch(line){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("\nInvalid option.");
            }


            System.out.print("\nSelect an option:" +
                    "\n\t1. ------------------" +
                    "\n\t2. ------------------" +
                    "\n\t3. ------------------" +
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
            System.out.println("College of Oarsmanship: Course Management System (CMS)");
            System.out.println("\t\tCTRL-D to exit.\n");
            run.login();

        }

    }
}
