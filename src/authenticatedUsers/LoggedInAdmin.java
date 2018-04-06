package authenticatedUsers;

import authenticationServer.AuthenticationToken;
import offerings.CourseOffering;

import java.util.Scanner;

public class LoggedInAdmin implements LoggedInAuthenticatedUser {

	private String name;
	private String surname;
	private String ID;
	private AuthenticationToken authenticationToken;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public AuthenticationToken getAuthenticationToken() {
		return authenticationToken;
	}
	
	public void setAuthenticationToken(AuthenticationToken authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public Boolean modifySystemState(Boolean currentState, int changeStateTo){
        if(currentState == false && changeStateTo == 1){
            System.out.println("\nStarting system.");
            return true;    //turn system ON
        }
        else if(currentState == false && changeStateTo == 0){
            System.out.println("\nThe system is already OFF.");
            return false;   //keep it OFF
        }
        else if(currentState == true && changeStateTo == 1){
            System.out.println("\nThe system is already ON.");
            return true;    //keep it ON
        }
        else{
            //state == true && change == 0
            System.out.println("\nStopping system.");
            return false;   //turn system OFF
        }
	}

	public void createCourses(){
	    Scanner input = new Scanner(System.in);
	    String line;
        System.out.print("\n\tGive filename: ");
        line = input.next();
        System.out.println("line");
    }
	 
}
