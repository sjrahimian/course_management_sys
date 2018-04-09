/**
 * @author Mohamed Moselhy, Abdullah Khan, Brandon Mathew, Sama Rahimian
 * @version 0.1
 * Winter cs2212
 *
 * Handle Authentication errors
 *
 */


public class AuthenticateError extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticateError(String str){
        super(str);
    }
}
