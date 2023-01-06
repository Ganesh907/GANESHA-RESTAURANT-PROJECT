import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class UIInterface extends DatabaseInterface{
	static Scanner s=new Scanner(System.in);
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
 	static void login1() throws SQLException, IOException{

 	
     String abc="",xyz="";
     int i=1;
     
   
     
     System.out.println("******** ....GANESHA RESTAURANT.... ********");
     System.out.println();
     
     
   
     while(i!=2) {
	System.out.print("Enter Username : ");
	abc=s.nextLine();
	System.out.print("Enter Password : ");
	xyz=s.nextLine();
	
	if(abc.equalsIgnoreCase(LoginInfo.login_one()) && xyz.equalsIgnoreCase(LoginInfo.login_1()) || abc.equalsIgnoreCase(LoginInfo.login_two()) && xyz.equalsIgnoreCase(LoginInfo.login_2())) {
		System.out.println();
		System.out.println();
		System.out.println("********  WELCOME TO GANESHA RESTAURANT ********");
		System.out.println();
		startApp();
		i++;
	}
	
	
	else {
	System.out.println("Login Failed");
	System.out.println();
	System.out.println("***** Please try Once Again *****");
	}
     }
	
}

	static void startApp() throws SQLException, IOException {
		

		System.out.println();
		while (true) {
			System.out.println("Select the operation to perform : ");
			System.out.println("1. Show the Menu Card");
			System.out.println("2. Find the Food Item by Name");
			System.out.println("3. Add More Food Item");
			System.out.println("4. Update Food Item Deatils");
			System.out.println("5. Delete Food Item");
			System.out.println("6. Generate Bill");
			System.out.println("7. Update Login Details");
			System.out.println("0. LOGOUT");
			System.out.println();
			System.out.print("Enter Your Choice : ");
			int choice=s.nextInt();
			System.out.println();
			
			if(choice==0)
				break;
			else {
				int id;
				String name;
				double price;
				char ch='y';
				
				
				switch(choice) {
					case 1: System.out.println(DatabaseInterface.getAll());
							System.out.println();
							break;
							
					case 2: System.out.print("Enter the Food Name : ");
			 		name = s.next();
			 		System.out.print(DatabaseInterface.getByName(name));
			 		System.out.println();
			 		break;
					
						/*
							System.out.print("Provide the ID of the Product: ");
							id = s.nextInt();
							System.out.println(DatabaseInterface.getById(id));
							
							break;
							*/
			 		
					case 3: System.out.print("Enter ID: ");
					id = s.nextInt();
					System.out.print("Enter Item Name: ");
//					name = s.next();
					name=br.readLine();
				
					System.out.print("Enter Item Price: ");
					price = s.nextDouble();
					System.out.println(DatabaseInterface.add(id, name, price));
					System.out.println();
					break; 
						
					case 4: System.out.print("Provide the ID of the Food Item to be updated: ");
					id = s.nextInt();
					System.out.println("Choose the detail to be updated:");
					System.out.println("1. Item Name");
					System.out.println("2. Item Price");
					System.out.print("Enter Your Choice: ");
					int upChoice = s.nextInt();
					switch(upChoice) {
						case 1: System.out.print("Enter New Name: ");
								name=br.readLine();
								System.out.println(DatabaseInterface.updateName(id, name));
								System.out.println();
								break;
						case 2: System.out.print("Enter New Price: ");
								price = s.nextDouble();
								System.out.println(DatabaseInterface.updatePrice(id, price));
								System.out.println();
								break;
						default: System.out.println("\n\n *** WRONG CHOICE *** \n\n");
								
					}
					break;
					
					
					
					case 5: System.out.print("Provide the ID of the Food Item to be deleted: ");
					id = s.nextInt();
					System.out.println(DatabaseInterface.delete(id));
					 System.out.println();
					break;
					
		
					case 6: 
						System.out.println(DatabaseInterface.getAll());
//						System.out.println();
						System.out.println();
						String count = "";
				
						while(ch!='n') {
							
							
							System.out.print("Provide the ID of the Product: ");
							id = s.nextInt();
//							System.out.println(ganesha.Bill(id));
							
					     	count+=ganesha.Bill(id);
							System.out.print("Do you want to add more items (y/n) : ");
							ch=s.next().toLowerCase().charAt(0);
							
							}
					        System.out.println("\n");
//					        System.out.println();
					        
					        System.out.println("__________________________________________________________________\n");
					        System.out.println("       ************* || GANESHA RESTAURANT || *************");
//					        System.out.println();
					        System.out.println("__________________________________________________________________");
//					        System.out.println();
					        System.out.println();
					        System.out.println("------------------------------------------------------------------");
					        System.out.println("|Name\t\t\t|Price\t\t|Qtn\t\t|Total");
							System.out.println("------------------------------------------------------------------");
					        System.out.print(count);
						    System.out.print("------------------------------------------------------------------\n");
							System.out.println(" Sub Total : \t\t\t\t\t\t|"+Total);
							System.out.print("------------------------------------------------------------------\n");
							double d1=(Total*0.09);
							System.out.println(" CGST @9% : \t\t\t\t\t\t "+d1);
							System.out.println(" SGST @9% : \t\t\t\t\t\t "+d1);
							System.out.print("------------------------------------------------------------------\n");
						    Total+=(Total*0.18);
							System.out.print(" Total Food Amount  : \t\t\t\t\t|"+Total+"|");
							System.out.print("\n------------------------------------------------------------------\n");
							System.out.println();
							
							System.out.println(time()+"\t\t  ...THANK YOU...");
//							System.out.println();
							System.out.println("__________________________________________________________________");
//							System.out.println("\t\t\t\t\t\tThank You");
							System.out.println("\n\n");
						   break;
							
						   
/*
					case 6: 
						System.out.println(DatabaseInterface.getAll());
						System.out.println(DatabaseInterface.generateBill());
						
						System.out.print("--------------------------------------------------------\n");
						System.out.println("Sub Total : \t\t\t\t\t|"+total);
						System.out.print("--------------------------------------------------------\n");
						System.out.println();
						double d=(total*0.09);
						System.out.println("CGST @9% : \t\t\t\t\t|"+d);
						System.out.println("SGST @9% : \t\t\t\t\t|"+d);
						System.out.print("--------------------------------------------------------\n");
						total+=(total*gst);
						System.out.print("Total Food Amount  : \t\t\t\t|"+total+"|");
						System.out.println();
//						System.out.print("\n--------------------------------------------------------\n");
						System.out.println("\t\t\t\t\t\tThank You");
						System.out.print(time());
						
						System.out.println();
			 		break;
						   

*/
						   
					case 7: 
						 String abc="",xyz="";
					     int i=1;
					     
					     System.out.println("Please Login again to Update Login Details ");
						 while(i!=2) {
								System.out.print("Enter Username : ");
							    abc=s.next();
								System.out.print("Enter Password : ");
								xyz=s.next();
								
								if(abc.equalsIgnoreCase(LoginInfo.login_one()) && xyz.equalsIgnoreCase(LoginInfo.login_1()) || abc.equalsIgnoreCase(LoginInfo.login_two()) && xyz.equalsIgnoreCase(LoginInfo.login_2())) {
									System.out.println();
									
									System.out.println(LoginInfo.allLogin());
									System.out.print("Provide the ID of the Food Item to be updated: ");
									id = s.nextInt();
									
									System.out.println("Choose the detail to be updated:");
									System.out.println("1. Username");
									System.out.println("2. Password");
									System.out.print("Enter Your Choice: ");
									int upChoice1 = s.nextInt();
									switch(upChoice1) {
										case 1: System.out.print("Enter New Userame: ");
												name = s.next();
												System.out.println(LoginInfo.updateName(id, name));
												System.out.println();
												break;
										case 2: System.out.print("Enter New Password: ");
											    String	password = s.next();
												System.out.println(LoginInfo.updatePassword(id, password));
												System.out.println();
												break;
										default: System.out.println("\n\n *** WRONG CHOICE *** \n\n");
												
									}
									
							
									
									
									i++;
								}
								
								else {
								System.out.println("Login Failed");
								System.out.println();
								System.out.println("***** Please try Once Again *****");
								}
							     }
						
						
					
						break;  
					default: System.out.println("\n\nEnter the correct choice\n\n"); 
					 System.out.println();
				}
			}
	}
	}

	
}
