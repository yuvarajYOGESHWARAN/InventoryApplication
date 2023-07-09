package Controller;

import java.io.BufferedReader;
import model.Product1;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import dao.LoginDAO;
import dao.ProductDao;
import model.Login;
import java.lang.*;



public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Login login = new Login();
		LoginDAO logindao = new LoginDAO();
		Product1 product = new Product1();
		ProductDao productdao = new ProductDao();
		
		int option,choice;
		do {
			System.out.println("1.Admin");
			System.out.println("2.Agent");
			System.out.println("3.Exit");
			System.out.println("-------------------------------------------");
			choice = Integer.parseInt(br.readLine());
			
			switch(choice) {
				case 1: 
					System.out.println("---------------------------------");
					System.out.println("Enter User Name");
					String username = br.readLine();
					System.out.println("Enter Password");
					String password = br.readLine();
					login.setUSER_NAME(username);
					login.setPASSWORD(password);
					boolean result = logindao.validate(login);
					if(result == true) {
						System.out.println("Login Successfull");
						System.out.println("----------------------------------");
						do {
							System.out.println("1.Add Product");
							System.out.println("2.Display Inventory Details");
							System.out.println("3.Logout");
							System.out.println("-------------------------------");
							option = Integer.parseInt(br.readLine());
							switch(option) {
							case 1:
								System.out.println("Enter the Product Name");
								String productName = br.readLine();
								System.out.println("Enter Product ID");
								int productId = Integer.parseInt(br.readLine());
								System.out.println("Enter min selling quantity");
								int minsell = Integer.parseInt(br.readLine());
								System.out.println("Enter the price of the Product");
								int price = Integer.parseInt(br.readLine());
								System.out.println("Enter the Quantity");
								int quantity = Integer.parseInt(br.readLine());
								System.out.println(productId+productName);
								 product.setPRODUCT_NAME(productName);
								 product.setPRODUCT_ID(productId);
								 product.setMINSELL(minsell);
								 product.setPRICE(price);
								 product.setQUANTITY(quantity);
								productdao.addProduct(product);
								System.out.println("-------------------------");
								break;
							case 2:
								productdao.display();
								break;
							case 3: break;	
							}
						}while(option != 3);
				
					} else {
						System.out.println("Username and password is not correct");
					}
					break;
				case 2:
					System.out.println("---------------------------------");
					System.out.println("Enter User Name");
					String agentusername = br.readLine();
					System.out.println("Enter Password");
					String agentpassword = br.readLine();
					login.setUSER_NAME(agentusername);
					login.setPASSWORD(agentpassword);
					result = logindao.validate(login);
					if(result == true) {
						System.out.println("Login Successfull");
						System.out.println("----------------------------------");
						do {
							System.out.println("1.Buy/Sell");
							System.out.println("2.Show History");
							System.out.println("3.Logout");
							System.out.println("-------------------------------");
							option = Integer.parseInt(br.readLine());
							switch(option) {
								case 1:
									System.out.println("Buy/Sell");
									String agent = br.readLine();
									if(agent.equalsIgnoreCase("buy")) {
										System.out.println("Enter the product id: ");
										int productId = Integer.parseInt(br.readLine());
										System.out.println("Enter the product name: ");
										String productname = br.readLine();
										System.out.println("Enter the min selling quantity: ");
										int minsell = Integer.parseInt(br.readLine());
										System.out.println("Enter the product price: ");
										int price = Integer.parseInt(br.readLine());
										System.out.println("Enter the product quantity: ");
										int quantity = Integer.parseInt(br.readLine());
										product.setPRODUCT_NAME(productname);
										 product.setPRODUCT_ID(productId);
										 product.setMINSELL(minsell);
										 product.setPRICE(price);
										 product.setQUANTITY(quantity);
										productdao.addProduct(product);
										System.out.println("__________________________________________________________");
									}
									else if(agent.equalsIgnoreCase("sell"))
									{
										System.out.println("__________________________________________________________");
										System.out.println("Enter the product id: ");
										int productId = Integer.parseInt(br.readLine());
										System.out.println("Enter the product quantity: ");
										int quantity = Integer.parseInt(br.readLine());
										if(productdao.quantityAvailable(productId, quantity))
										{
											int total = productdao.totalCost(productId, quantity);
											System.out.println("__________________________________________________________");
											System.out.println("Total cost is "+total);
											System.out.println("__________________________________________________________");
											System.out.println("Confirm Booking(Yes/No)");
											String booking = br.readLine();
											System.out.println("__________________________________________________________");
											
										}
									}
								
									case 2:
										productdao.display();
										break;
									case 3: break;	
									}
							
							
							
							
							
						}while(option != 3);
					}  else {
						System.out.println("Username and password is not correct");
					}
					break;
			}
		} while(choice != 3);
	}

}
