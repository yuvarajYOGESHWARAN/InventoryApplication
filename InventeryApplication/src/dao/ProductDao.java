package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionManager.ConnectionManager;
import model.Product1;

public class ProductDao {
	public void addProduct(Product1 product) throws ClassNotFoundException, SQLException {
		int id = product.getPRODUCT_ID();
		String name = product.getPRODUCT_NAME();
		int minsell = product.getMINSELL();
		int price = product.getPRICE();
		int quantity = product.getQUANTITY();
		System.out.println(id+name);
		
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		String query = "insert into " + "PRODUCT1(PRODUCT_ID,PRODUCT_NAME,MINSELL,PRICE,QUANTITY)"
				+"values(?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, minsell);
		ps.setInt(4, price);
		ps.setInt(5, quantity);
		
		
		ps.executeUpdate();
	conn.closeConnection();
	}
	public void display() throws ClassNotFoundException, SQLException {
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM PRODUCT");
		while(rs.next()) {
			System.out.println(rs.getString("PRODUCT_NAME")+"|"+rs.getInt("PRODUCT_ID")+"|"+rs.getInt("MINSELL")+"|"
					+rs.getInt("PRICE")+"|"+rs.getInt("QUANTITY"));
		}
		conn.closeConnection();
	}
	
	public boolean quantityAvailable(int productid, int quantity) throws ClassNotFoundException, SQLException
	{
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		PreparedStatement st = con.prepareStatement("select quantity, minsell from product where productid=?");
		st.setInt(1,productid);
		ResultSet rs = st.executeQuery();
		
		if(rs.next())
		{
			if(quantity<=rs.getInt("quantity") && quantity<rs.getInt("minsell"))
			{
				conn.closeConnection();
				return true;
			}
			else
			{
				conn.closeConnection();
				return false;
			}
		}
		return false;
	}
	
	
	public int totalCost(int productid, int quantity) throws SQLException, ClassNotFoundException
	{
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		PreparedStatement st = con.prepareStatement("select price from product where productid=?");
		st.setInt(1,productid);
		ResultSet rs = st.executeQuery();
		
		int total=0;
		if(rs.next())
		{
			total = quantity*rs.getInt("price");
		}
		conn.closeConnection();
		return total;
	}
//	public void displayDetails(Product1 product) throws ClassNotFoundException, SQLException {
//		int id = product.getPRODUCT_ID();
//		String name = product.getPRODUCT_NAME();
//		int price = product.getPRICE();
//	}
}
