/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Parese and build login database
 *
 */

import java.io.*;
import java.util.*;

public class Database{
private Hashtable<Integer, String[]> database;

    /**
     *
     * @param file  filename of file to parse and build login database
     * @throws IOException
     */
    public Database(String file) throws IOException {
        this.database = new Hashtable<>();
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            br.readLine(); //skip the first line
            buildLoginDB(br);
            br.close();
    }

    /**
     * parse file and build loing database
     * @param br takes buffered reader
     * @throws IOException
     */
    private void buildLoginDB(BufferedReader br) throws IOException{
        String line;

        while((line = br.readLine()) != null){
            String[] broken = line.split("\t");
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
