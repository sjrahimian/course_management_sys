package authenticatedUsers;

import authenticationServer.AuthenticationToken;
import offerings.CourseOffering;

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

	public Boolean system_on_off(Boolean state, int change){
		//turn system on
		if(state == false && change == 1){
			return true; //turn system on
		}
		else if(state == true && change == 0){
			return false; //turn system off
		}
		else{
			String val;
			switch (change){
				case 0: val = "OFF";
					break;
				case 1: val = "ON.";
					break;
				default: val = "ERROR in system start";
			}

			System.out.println("The system is already " + val);
		}

		//turn system off
		return false;

	}
	 
}
