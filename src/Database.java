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
    public Database(String file) throws IOException {
        this.database = new Hashtable<>();
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            br.readLine(); //skip the first line
            buildDB(br);
            br.close();
    }

    /**
     * parse through textfile and add them to
     * @param br takes buffered reader
     * @throws IOException
     */
    private void buildDB(BufferedReader br) throws IOException{
        String line;

        while((line = br.readLine()) != null){
            String[] broken = line.split(" ");
            this.database.put(Integer.parseInt(broken[2]),broken);
        }
    }

    public Boolean containsUser(int id){
        return this.database.containsKey(id);
    }

    public String[] getAllData(int id){
        return this.database.get(id);
    }

    public String getUserType(int id){
        String[] temp = this.database.get(id);
        return temp[4];
    }

}
