
//import java.sql.SQLException;


public class GANESHA_RESTAURANT{
	public static void main(String[] args) {
		try {
			DatabaseInterface.dbConnect();
			UIInterface.login1();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			//System.out.println("finally");
			try {
				DatabaseInterface.dbDisconnect();
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
		}
}
}


