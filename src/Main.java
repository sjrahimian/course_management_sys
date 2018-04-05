import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Boolean sys_state = false;  //system is off
        Authenticate auth = new Authenticate();
        Database db = new Database("userDB.txt","UD");
        Scanner input = new Scanner(System.in);
        String password, id;



        for (; ; ) {
            System.out.print("User ID: ");
            id = input.next();


            if(!db.containsUser(Integer.parseInt(id))){
                System.out.println("No such user. Try again.\n");
            }
            else{
                String[] user = db.getAllData(Integer.parseInt(id));
                System.out.print("Password: ");
                password = input.next(); //for console testing --> disable this and enable next 3 lines
//                Console con = System.console();
//                char[] passString = con.readPassword();
//                password = new String(passString);

                try {
                    if (auth.checkPassword(auth.encode(password), user[3])) {
                        System.out.println("\nLogging in...");
                        break;
                    } else {
                        System.out.println("Incorrect password. Try again.\n");
                    }
                }
                catch (AuthenticateError authenticateError) {
                    authenticateError.printStackTrace();
                }
            }

        }


        //generate whenever someone logs in and set methods
        //id will determine user type
        //increment token ID
        //session date and time

//        AuthenticationToken token = new AuthenticationToken();
//
//        LoggedInUserFactory log = new LoggedInUserFactory();
//        log.createAuthenticatedUser(token);

//        switch statemnt after tokens

    }
}
