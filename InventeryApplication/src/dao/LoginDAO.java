package dao;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import ConnectionManager.ConnectionManager;
import model.Login;

public class LoginDAO {
	
	public boolean validate(Login login) throws ClassNotFoundException, SQLException {
		String username = login.getUSER_NAME();
		String password = login.getPASSWORD();
		
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM LOGIN");
		
		while(rs.next()) {
			if(username.equals(rs.getString("USER_NAME")) && password.equals(rs.getString("PASSWORD"))) {
				conn.closeConnection();
				return true;
			}
		}
		conn.closeConnection();
		return false;
		
	}
	
}
