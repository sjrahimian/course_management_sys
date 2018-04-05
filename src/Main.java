import authenticatedUsers.*;
import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;




public class Main {
    private static Boolean sys_state = false;  //system is off

    public static void main(String[] args) throws IOException {
        Authenticate auth = new Authenticate();
        AuthenticationToken token;
        Database db = new Database("userDB.txt","UD");
        Scanner input = new Scanner(System.in);
        String password, id;
        String[] possibleUser;
        int counter = 0;

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
                            System.out.println("\nLogging in on " + OS + " on " + now.format(dtf) + "\n");
                            //generate whenever someone logs in and set methods
                            //id will determine user type
                            //increment token ID
                            //session date and time
                            token = new AuthenticationToken();
                            token.setSessionID((int) System.currentTimeMillis());
                            token.setTokenID(counter);
                            token.setUserType(possibleUser[4]);
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

        System.out.println("\tCollege of Oarsmanship & Business: Course Management System (CMS)\n");

        //create new log session
        //switch statement after tokens
        LoggedInUserFactory log = new LoggedInUserFactory();
        LoggedInAdmin admin;
        LoggedInInstructor instructor;
        LoggedInStudent student;

        switch(token.getUserType()){
            case "Admin":
                admin = (LoggedInAdmin) log.createAuthenticatedUser(token);
                System.out.println("Welcome Administrator " + possibleUser[0] + " " + possibleUser[1]);
                break;
            case "Instructor":
                instructor = (LoggedInInstructor) log.createAuthenticatedUser(token);
                System.out.println("Welcome Instructor " + instructor.getName() +".");
                break;
            case "Student":
                student = (LoggedInStudent) log.createAuthenticatedUser(token);
                System.out.println("User: " + student.getName() + student.getSurname());
        }



//        else{
//





    }
}
