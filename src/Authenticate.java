/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Will perform password authentication (e.g., comparison) and encryption activity
 *
 *
 */

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class Authenticate {
    private String salt = "w3HA6WMQzEAe81eI1d7lCbSAsGHX2V1C";
    private static String cipher_info = "AES/ECB/PKCS5Padding";

    public Authenticate(){}

    /** Checks length of the password to minimum requirement (8 inclusive)
     * @param password string: takes the user's password
     * @return boolean true if length is minimum 8 inclusive, otherwise false
     */
    public Boolean checkLength(String password) {
        if(password.length() >= 6){
            return true;
        }

        return false;
    }

    /** Compare two passwords
     * @param firstPW string: takes the inital entry of user's password
     * @param secondPW string: takes the second password to compare
     * @return boolean true if equal, otherwise false
     */
    public Boolean checkPassword(String firstPW, String secondPW){
        if(firstPW.equals(secondPW))
            return true;
        else
            return false;
    }


    /** Encryption of password using standard Java's AES encryption algorithm
    * @param toEncrypt: string user's password to be encrypted
     * @return encrypted string password
    */
    public String encode(String toEncrypt) throws AuthenticateError {
        String key = "eL1vLWk3tn6oP6vN";
        String saltyPassword = salt + toEncrypt;

        //setup encryption methods
        String encryptedValue;
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

        try {   //encrypt the user password
            Cipher cipher = Cipher.getInstance(cipher_info);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(saltyPassword.getBytes());
            encryptedValue = Base64.getEncoder().encodeToString(encrypted);
        }
        catch(Exception e){
            throw new AuthenticateError(e.toString());
        }

        //make sure encrypted is not null
        if(encryptedValue == null) {
            throw new AuthenticateError("Encryption Failure; value: null");
        }

        return encryptedValue;


    }

//    /** Decryption of string using standard Java's AES decryption algorithm
//     * @param toDecrypt string - encrypted to be decrypted
//     * @return decrypted string password
//     */
//    public String decode(String toDecrypt) throws AuthenticateError {
//        //setup encryption methods
//        String key = "eL1vLWk3tn6oP6vN";
//        String decryptedSaltyPassword;
//        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
//
//        try {   //decrypt the user password
//            Cipher cipher = Cipher.getInstance(cipher_info);
//            cipher.init(Cipher.DECRYPT_MODE, aesKey);
//            byte[] decordedValue = Base64.getDecoder().decode(toDecrypt);
//            byte[] decValue = cipher.doFinal(decordedValue);
//            decryptedSaltyPassword = new String(decValue);
//        }
//        catch(Exception e){
//            throw new AuthenticateError(e.toString());
//        }
//
//        //make sure decrypted string is not null
//        if(decryptedSaltyPassword == null) {
//            throw new AuthenticateError("Decryption Failure; value: null");
//        }
//
//
//        //remove salt
//        String pw = decryptedSaltyPassword.replace(salt,"");
//        return pw;
//
//
//    }

}



