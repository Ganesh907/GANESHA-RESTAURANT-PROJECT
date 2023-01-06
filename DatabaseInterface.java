import java.util.Scanner;

//import database.DatabaseInterface;

//import database.DatabaseInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseInterface {
	static Scanner s=new Scanner(System.in);
	static Connection con;
//	static int id1;
//	static int id;
//	static double total=0.0;
//	static double gst=0.18;
//	static Double Total=0.0;
	
	 int id1;
	 int id;
    	static double total=0.0;
	 double gst=0.18;
	static double Total=0.0;

	public static void dbConnect() throws ClassNotFoundException, SQLException {
		//1. Load the Driver Class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2. Establish Connection with Database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", 
											"root", "");

		//System.out.println(con);
	}
	
	static void dbDisconnect() throws SQLException {
		con.close();
	}
	

	
//	get Food Menu Card
	public static String getAll() throws SQLException {
		String query = "select * from foodmenu";
		
		Statement stat = con.createStatement();
		
		ResultSet rs = stat.executeQuery(query);

		
		String result = "-----------------------------------------------------\n";
			   result += "|ID\t|Item Name\t\t\t|Item Price\n";
       	       result += "-----------------------------------------------------\n";
		while(rs.next()) {
			result += "|" + rs.getInt("item_id") + 
					"\t|" + rs.getString(2) + 
					"\t\t\t|" + rs.getDouble(3) +"\n";
		}
		       result += "-----------------------------------------------------\n";
		
		return result;
	}
	
	
//	Get Food Item By Name
	
	public static String getByName(String name) throws SQLException {
		String query = "select * from foodmenu where item_name like ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, "%" + name + "%");
		
	
		
		
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			output += "\n---------------------------------------------------\n";
			output += "|ID\t|Item Name\t\t\t|Item Price\n";
			output += "---------------------------------------------------\n";
			output += "|" + rs.getInt("item_id") + 
					"\t|" + rs.getString(2) + 
					"\t\t\t|" + rs.getDouble(3) +"\n";
			output += "---------------------------------------------------\n";
		}		
		else
			output += "\n\n **** No Record Found **** \n\n";
		
		return output;
	}
	
	
	
//   Add Food Item ToMenu Card
	
	public static String add(int id, String name, double price)
			throws SQLException {
		String query = "insert into foodmenu values(?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setDouble(3, price);
		
		int i = ps.executeUpdate();		
		String result="";
		
		if(i>0)
			result = "\nInserted Successfully.......";
		else
			result = "\nInsertion Failed";
		return result;	
	}
	

	
	
// 		Delete Food Item From Menu Card
	
	public static String delete(int id) throws SQLException {
		String query = "delete from foodmenu where item_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "\nDeleted Successfully.......";
		else 
			output += "\nDelete Failed";
		
		return output;
	}

	
	
//		Update Food Item Name In Menu Card by Id
	
	public static String updateName(int id, String name) throws SQLException {
		String query = "update foodmenu set item_name = ? where item_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "\nName Updated Successfully.....";
		else 
			output += "\nUpdate Failed";
		
		return output;
	}
	
	
//	Update Food Item Price In Menu Card by Id

	public static String updatePrice(int id, double price) throws SQLException {
		String query = "update foodmenu set item_price = ? where item_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setDouble(1, price);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "\nPrice Updated Successfully.....";
		else 
			output += "\nUpdate Failed";
		
		return output;
	}



	
//	Get Food Item By ID
	
	
	public static String getById(int id) throws SQLException {
		String query = "select * from foodmenu where item_id = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		String result="";
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			result = "-----------------------------------------------------\n";
			result += "ID\tItem Name\tItem Price\n";
			result += "-----------------------------------------------------\n";
			result += rs.getInt("item_id") + "\t"
					+ rs.getString(2) + "\t"
					+ rs.getDouble(3) + "\n";
		}
		else
			result = "\nNo Records Found\n";	
		
		return result;
	}
	
	
	
	
	
	
	
//  Generate Bill
	
	public static String generateBill() {
		double i=0;
		int j;
		int i1=1;
		char ch='y';
		String sum="";
	
		while(ch!='n') {
			System.out.println();
			System.out.print("Enter Food Price : ");	 
			 i=s.nextInt();
			 System.out.print("Enter Food Quantity : ");	
			 j=s.nextInt();
			sum+="\n|"+i+"\t\t\t|"+j+"\t\t\t|"+(i*j);
			total+=(i*j);
			System.out.print("Do you want to add more items (y/n) : ");
			ch=s.next().toLowerCase().charAt(0);

		}
		while(i1==1) {
			System.out.println();
			System.out.print("--------------------------------------------------------\n");
		    System.out.print("|Item Price\t\t|Item Qtn\t\t|Total \n");
			System.out.print("--------------------------------------------------------");
			break;
		 }
		i1++;
		
		return sum;
	}
	
	
	static String time() {
		String str="";
		System.out.print(" Date & Time : ");
		DateTimeFormatter d=DateTimeFormatter.ofPattern("yyyy/MM/dd & HH:mm:ss");
		LocalDateTime now=LocalDateTime.now();
		System.out.print(d.format(now));
		return str;
	}
}


class ganesha extends DatabaseInterface{
	
public static String Bill(int id) throws SQLException {
	String query = "select item_name,item_price from foodmenu where item_id = ?";
	String result="";
	

	PreparedStatement ps = con.prepareStatement(query);		
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();

	System.out.print("Enter Quantity : ");
	int	qtn=s.nextInt();

	if(rs.next()) {
	
		result += "|"+rs.getString(1) + "\t\t|"
				+ rs.getDouble(2) + "\t\t|"
				+ qtn+ "\t\t|"
			    + rs.getDouble(2)*qtn + "\n";

		Total+=rs.getDouble(2)*qtn;
//		result += "Total Amount is : "+rs.getDouble(2)*n11;
	}
	else {
		result = "\nNo Records Found\n";	
	}
	return result;
}
}



class LoginInfo extends DatabaseInterface{
	
	
	static String allLogin() throws SQLException {
		String query = "select * from login";
		
		Statement stat = con.createStatement();
		
		ResultSet rs = stat.executeQuery(query);

		
		String result = "--------------------------------------\n";
			   result += "|ID\t|Username\t|Password\n";
       	       result += "--------------------------------------\n";
		while(rs.next()) {
			result += "|" + rs.getInt(1) + 
					"\t|" + rs.getString(2) + 
					"\t|" + rs.getString(3) +"\n";
		}
		       result += "--------------------------------------\n";
		
		return result;
	}
	
	
		public static String login_one() throws SQLException {
			String query = "select * from login where id = ?";
			int id=1;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			String result1="";
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result1 += rs.getString(2);
			}
			else
				result1 = "\nNo Records Found\n";	
			
			return result1;
		}
		public static String login_1() throws SQLException {
			String query = "select * from login where id = ?";
			
			int id=1;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			String result1="";
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
		
				result1 += rs.getString(3);
					
			}
			else
				result1 = "\nNo Records Found\n";	
			
			return result1;
		}
		
		
		public static String login_two() throws SQLException {
			String query = "select * from login where id = ?";
			int id=2;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			String result1="";
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result1 += rs.getString(2);
			}
			else
				result1 = "\nNo Records Found\n";	
			
			return result1;
		}
		public static String login_2() throws SQLException {
			String query = "select * from login where id = ?";
			
			int id=2;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			String result1="";
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
		
				result1 += rs.getString(3);
					
			}
			else
				result1 = "\nNo Records Found\n";	
			
			return result1;
		}


		//Update UserName 
		
		public static String updateName(int id, String name) throws SQLException {
		String query = "update login set Username = ? where ID = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "\nUsername Updated Successfully.....";
		else 
			output += "\nUpdate Failed";
		
		return output;
		}

			
		//Update Password
		public static String updatePassword(int id, String password) throws SQLException {
			String query = "update login set Password = ? where ID = ?";
			
			String output = "";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, password);
			ps.setInt(2, id);
			
			int i = ps.executeUpdate();
			
			if(i>0)
				output += "\nPassword Updated Successfully.....";
			else 
				output += "\nUpdate Failed";
			
			return output;
		}
		
		
}



