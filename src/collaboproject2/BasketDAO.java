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
	public void addBasket(BasketVO vo) {
		Connection con=getConnection();
		PreparedStatement psmt=null;
		String sql="insert into baskettbl(id,no,name,country,price) values(?,?,?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setInt(2, vo.getNo());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getCountry());
			psmt.setInt(5, vo.getPrice());
			
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
					
					int no=rs.getInt(3);
					String name=rs.getString(4);
					String country=rs.getString(5);
					int price=rs.getInt(6);
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
		
		
		public void delBasket(String name,String id) {
			Connection con=getConnection();
			PreparedStatement psmt=null;
			String sql="delete from baskettbl where id=? and name=?";
			try {
				psmt=con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, name);				
				psmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt);
			}
		}
		
		
	
}
