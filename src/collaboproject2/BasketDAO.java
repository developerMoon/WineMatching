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
		//중복되는거 확인하기\
		Vector<BasketVO> vec=getBasket(vo.getId());
		if(vec.isEmpty()) {
			addBasket(vo,0);
		}else {
			int count=0;
			for(BasketVO bvo:vec) {
				if( (bvo.getNo()==vo.getNo()) ) {
					countUp(vo.getName(), vo.getId(), bvo.getCount(), vo.getCount());
					count++;
				}else if( bvo.getName().equals(vo.getName()) ) {
					countUp(vo.getName(), vo.getId(), vo.getCount());
					count++;
				}
			}
			if(count==0) {
				addBasket(vo, 0);
			}
			
		}
	}	
	
	//장바구니에 넣기
	public void addBasket(BasketVO vo,int i) {
		Connection con=getConnection();
		PreparedStatement psmt=null;
		String sql="insert into baskettbl(id,no,name,country,price,count) values(?,?,?,?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setInt(2, vo.getNo());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getCountry());
			psmt.setInt(5, vo.getPrice());
			psmt.setInt(6, vo.getCount());
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
				rs=psmt.executeQuery();   //여기서 오류남
				while(rs.next()) {
					int no=rs.getInt(2);
					String name=rs.getString(3);
					String country=rs.getString(4);
					int price=rs.getInt(5);
					int count=rs.getInt(6);
					BasketVO vo=new BasketVO(id, no, name, country, price,count);
					vec.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt, rs);
			}
			return vec;
		}
		public int getCount(String name,String id) {
			Connection con=null;
			PreparedStatement psmt=null;
			ResultSet rs=null;
			int count=0;
			String sql="select * from baskettbl where id=? and name=?";
			try {
				con=getConnection();
				psmt=con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, name);
				rs=psmt.executeQuery();
				while(rs.next()) {
					count=rs.getInt(6);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt, rs);
			}
			return count;
		}

		//삭제
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
		//삭제
		public void delBasket(String id) {
			Connection con=getConnection();
			PreparedStatement psmt=null;
			String sql="delete from baskettbl where id=?";
			try {
				psmt=con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt);
			}
		}	
		//개수증가
		public void countUp(String name,String id,int count) {
			Connection con=getConnection();
			PreparedStatement psmt=null;
			int newCount=count+1;
			String sql="update baskettbl set count=? where name=? and id=?";
			try {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, newCount);
				psmt.setString(2, name);				
				psmt.setString(3, id);
				psmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt);
			}
		}
		public void countUp(String name,String id,int count,int addCount) {
			Connection con=getConnection();
			PreparedStatement psmt=null;
			int newCount=count+addCount;
			String sql="update baskettbl set count=? where name=? and id=?";
			try {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, newCount);
				psmt.setString(2, name);				
				psmt.setString(3, id);
				psmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt);
			}
		}
		//개수 감소
		public void countDown(String name,String id,int originCount,int count) {
			Connection con=getConnection();
			PreparedStatement psmt=null;
			int newcount=originCount-count;
			String sql="update baskettbl set count=? where name=? and id=?";
			try {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, newcount);
				psmt.setString(2, name);				
				psmt.setString(3, id);
				psmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, psmt);
			}
		}
	
}
