/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Frontend that presents user end functions and accepts clients requests via command line style interface
 * ~~where the magic happens~~
 *
 */

import java.io.IOException;

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
