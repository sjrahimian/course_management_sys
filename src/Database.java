import offerings.CourseOffering;
import offerings.OfferingFactory;

import java.io.*;
import java.util.*;

public class Database{
private Hashtable<Integer, String[]> database;

    /**
     *
     * @param file  filename of file to parese
     * @param choice Use either 'UD' for UserDatabase or 'CO' for CourseOffering respectively.
     * @throws IOException
     */
    public Database(String file, String choice) throws IOException {
        if (choice.equals("UD")){
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            br.readLine(); //skip the first line
            buildDB(br);
            br.close();
        }
        else if(choice.equals("CO")){
            buildCourseOffering(file);
        }
        else{
            System.out.println("!!!!!!!!!\n  >>>>>ERROR :: Use either \'UD\' or \'LC\' for UserDatabase or LoadCourses respectively.\n!!!!!!!!!");
        }
    }

    /**
     *
     * @param file filename that stores course information
     * @throws IOException
     */
    private void buildCourseOffering(String file) throws IOException {
        OfferingFactory factory = new OfferingFactory();
        BufferedReader br = new BufferedReader(new FileReader(new File(file)));
        CourseOffering courseOffering = factory.createCourseOffering(br);
        br.close();

    }


    private void buildDB(BufferedReader br) throws IOException{
        String line;

        while((line = br.readLine()) != null){
            String[] broken = line.split(" ");
            Integer num = Integer.parseInt(broken[2]);
            if(num == null)
                this.database.put(num,broken);
        }
    }

    public Boolean containsUser(int id){
        return this.database.containsKey(id);
    }

    public String[] getAllData(int id){
        return this.database.get(id);
    }

    public Integer getUserType(int id){
        String[] temp = this.database.get(id);
        return Integer.parseInt(temp[4]);
    }

}
