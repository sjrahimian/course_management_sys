import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;

import java.io.*;



public class Main {

    public static void main(String[] args) throws IOException {
        Authenticate auth = new Authenticate();
        Database db = new Database("userDB.txt","UD");

//        for (; ; ) {
//            System.out.print("User ID: ");
//            Scanner scan = new Scanner(System.in);
//            String id = scan.next();
////
//            System.out.print("Password: ");
//            String password = scan.next(); //for console testing --> disable this and enable next 3 lines
////        Console con = System.console();
////        char[] passString = con.readPassword();
////        String password = new String(passString );
//
//
//            try {
//                String pass2 = "bart";
//                if (auth.checkPassword(auth.encode(password), auth.encode(pass2))) {
//                    System.out.println(auth.encode(password));
//                    break;
//                } else {
//                    System.out.println("Incorrect password. Try again.\n");
//                }
//            } catch (AuthenticateError authenticateError) {
//                authenticateError.printStackTrace();
//            }
//
//        }


        AuthenticationToken token = new AuthenticationToken();


        LoggedInUserFactory log = new LoggedInUserFactory();
    }
}
