package lt.world.app.boxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class BoxDAOImpl implements BoxDAO {

//	private DataSource dataSource;
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	
	public Connection getConnection() throws SQLException, ClassNotFoundException {

//		<property name="driverClassName" value="com.mysql.jdbc.Driver" /> sitas nedadetas metode lyginant su tuo, kas kisama i beansa
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("username", "root");
	    connectionProps.put("password", "rootpass1");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testapp?useSSL=false", connectionProps);
	    System.out.println("Connected to database");
	    return conn;
	}
	
	
	
	@Override
	public void save(Box box) {
		String query = "insert into Boxes (color, size) values (?,?)"; //cia id ismeciau, nes darysiu kad jis autoincrement
		Connection con = null;
		
		PreparedStatement ps = null;
		try{
//			con = dataSource.getConnection();
			con=getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, box.getColor());
			ps.setDouble(2, box.getSize());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Box saved succesfully");
			}else System.out.println("Box save failed");
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException o) {		//PRIDETA KAIP Class.forName("com.mysql.jdbc.Driver"); PRIDEJIMO PASEKME
			o.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Box getById(Long id) {
		String query = " select color, size from Boxes where id = ?";
		Box emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
//			con = dataSource.getConnection();
			con=getConnection();
			
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Box box = new Box();
				box.setId(id);
				box.setColor(rs.getString("color"));
				box.setSize(rs.getDouble("size"));
				System.out.println("Box Found::"+box);
			}else{
				System.out.println("No box found with id="+id);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException o) {		//PRIDETA KAIP Class.forName("com.mysql.jdbc.Driver"); PRIDEJIMO PASEKME
			o.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	@Override
	public void update(Box box) {
		String query = " update Boxes set color=?, size=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
//			con = dataSource.getConnection();
			con=getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, box.getColor());
			ps.setDouble(2, box.getSize());
			ps.setLong(3, box.getId());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Box updated with id="+box.getId());
			}else System.out.println("No box found with id="+box.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException o) {		//PRIDETA KAIP Class.forName("com.mysql.jdbc.Driver"); PRIDEJIMO PASEKME
			o.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void deleteById(Long id) {
		String query = " delete from Boxes where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
//			con = dataSource.getConnection();
			con=getConnection();
			
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Box deleted with id="+id);
			}else System.out.println("No box found with id="+id);
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException o) {		//PRIDETA KAIP Class.forName("com.mysql.jdbc.Driver"); PRIDEJIMO PASEKME
			o.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Box> getAll() {
		String query = " select id, color, size from Employee";
		List<Box> boxList = new ArrayList<Box>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
//			con = dataSource.getConnection();
			con=getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Box box = new Box();
				box.setId(rs.getLong("id"));
				box.setColor(rs.getString("color"));
				box.setSize(rs.getDouble("size"));
				boxList.add(box);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException o) {		//PRIDETA KAIP Class.forName("com.mysql.jdbc.Driver"); PRIDEJIMO PASEKME
			o.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boxList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void addBox(Box box) {
//		boxes.add(new Box (box.getSize(), box.getColor()));	
//		System.out.println("Added new box to the array.");
//	}
//
//	@Override
//	public List<Box> getAllBoxes() {
//		return boxes;
//	}
//
//	@Override
//	public void updateBox(Box box) {
//		int iToUpdate = 0;
//		
//		for (Box item : boxes) {
//			if(box.getId() == item.getId()) {
//				iToUpdate = boxes.indexOf(item);
//			}
//		}
//		
//		boxes.get(iToUpdate).setColor(box.getColor());
//		boxes.get(iToUpdate).setSize(box.getSize());
//		System.out.println("Update completed");
//	}
//
//	@Override
//	public void deleteBox (Long id) {
//		int i = 0;
//		
//		for (Box item: boxes) {
//			if(item.getId() == id) {
//				i = boxes.indexOf(item);
//			}
//		}
//		boxes.remove(i);
//		System.out.println("Box deleted");
//	}
//	
//	public void printArray() {
//		for (Box box : boxes) {
//			System.out.println(box);
//		}
//	}
//	
//	public Box getOneById(Long id) {
//		Box box = new Box();
//		for (Box item : boxes) {
//			if(id == item.getId()) {
//				box = item;
//			}
//		}
//		return box;
//	}
		
}
