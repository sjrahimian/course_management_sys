import authenticatedUsers.*;
import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Boolean sys_state = false;  //system is off
        Authenticate auth = new Authenticate();
        AuthenticationToken token;
        Database db = new Database("userDB.txt","UD");
        Scanner input = new Scanner(System.in);
        String password, id;
        int counter = 0;




        for (; ; ) {
            //try for user id
            System.out.print("User ID: ");
            id = input.next();

            if(!db.containsUser(Integer.parseInt(id))){
                System.out.println("No such user. Try again.\n");
            }
            else{   //user id in database
                String[] possibleUser = db.getAllData(Integer.parseInt(id));
                //try for password
                System.out.print("Password: ");
    //disable input.next() --> for IDE testing, then enable following 3 lines for console testing
                password = input.next();
//                Console con = System.console();
//                char[] passString = con.readPassword();
//                password = new String(passString);

                try {
                    if (auth.checkPassword(auth.encode(password), possibleUser[3])) {
                        System.out.println("\nLogging in...");
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
        LoggedInUserFactory log = new LoggedInUserFactory();
        LoggedInAdmin admin;
        LoggedInInstructor instructor;
        LoggedInStudent student;

        switch(token.getUserType()){
            case "Admin":
                admin = (LoggedInAdmin) log.createAuthenticatedUser(token);
            case "Instructor":
                instructor = (LoggedInInstructor) log.createAuthenticatedUser(token);
            case "Student":
                student = (LoggedInStudent) log.createAuthenticatedUser(token);
        }

        /**
         //-------------------------\\
         || ++ CHECK SYSTEM STATE ++ ||
         \\-------------------------//
         **/

        if(!sys_state && (token.getUserType().equals("Instructor") || token.getUserType().equals("Student"))){
            //System is off
            System.out.println("The system is offline. Please wait for System Administrator to switch it on.");
            //continue but only let admin
        }
        else{
            System.out.println("Welcome Administrator " + admin.getName() +".");
        }




    }
}
