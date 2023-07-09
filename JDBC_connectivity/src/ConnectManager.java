import java.sql.*;
public class ConnectManager {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PERSONDETAILS", "root", "1024");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select*from person");
			while(rs.next()) {
				System.out.println(rs.getInt("id")+"   |" + rs.getString("username") + "  |" + rs.getString("address"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(con != null) {
			System.out.println("Connection Established");
		} else {
			System.out.println("Connection not Established");
		}
	}

}
