package collaboproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BasketDAO {

	public Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/javadb?useSSL=false";
			return DriverManager.getConnection(url,"root", "12345");
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public void dbClose(Connection con, PreparedStatement psmt, ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
			if(psmt!=null)
				psmt.close();
			if(con!=null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void dbClose(Connection con, PreparedStatement psmt) {
		try {
		
			if(psmt!=null)
				psmt.close();
			if(con!=null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//장바구니에 집어넣기
	public void addBasket(String id,int no, String name, String country, int price) {
		Connection con=getConnection();
		PreparedStatement psmt=null;
		String sql="insert into baskettbl values(?,?,?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, no);
			psmt.setString(3, name);
			psmt.setString(4, country);
			psmt.setInt(5, price);
			
			psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(con, psmt);
		}
	}
	
	//장바구니에있는거 가져오가
		public Vector<BasketVO> getBasket(String id) {
			Connection con=getConnection();
			PreparedStatement psmt=null;
			ResultSet rs=null;
			Vector<BasketVO> vec=new Vector<>();
			String sql="select * from baskettbl where id=?";
			try {
				psmt=con.prepareStatement(sql);
				psmt.setString(1, id);				
				rs=psmt.executeQuery();
				while(rs.next()) {
					
					int no=rs.getInt(2);
					String name=rs.getString(3);
					String country=rs.getString(4);
					int price=rs.getInt(5);
					BasketVO vo=new BasketVO(id, no, name, country, price);
					vec.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt, rs);
			}
			return vec;
		}
	
	
}
