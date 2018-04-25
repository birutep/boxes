package lt.world.app.boxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoxDAOImpl implements BoxDAO {
	
	
	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;
	
	@Override
	public void save(Box box) {
		String query = "insert into Boxes (color, size) values (?,?)";
		Connection con = null;
		
		PreparedStatement ps = null;
		try{
//			con = dataSource.getConnection();
//			con=getConnection();
			con=dataSource.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, box.getColor());
			ps.setDouble(2, box.getSize());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Box saved succesfully");
			}else System.out.println("Box save failed");
		}catch(Exception e){					//Pirmineje versijoje gaude SQLException, ClassNotFoundException (prideta i kompanija Class.forName("com.mysql.jdbc.Driver"))
			e.printStackTrace();
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
		Box box = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con=dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				box = new Box();
				box.setId(id);
				box.setColor(rs.getString("color"));
				box.setSize(rs.getDouble("size"));
				System.out.println("Box Found:"+ box);
			}else{
				System.out.println("No box found with id="+id);
			}
		}catch(Exception e){					//Pirmineje versijoje gaude SQLException, ClassNotFoundException (prideta i kompanija Class.forName("com.mysql.jdbc.Driver"))
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return box;
	}

	@Override
	public void update(Box box) {
		String query = " update Boxes set color=?, size=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con=dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, box.getColor());
			ps.setDouble(2, box.getSize());
			ps.setLong(3, box.getId());			
			int out = ps.executeUpdate();

			if(out !=0){
				System.out.println("Box updated with id="+box.getId());
			}else System.out.println("No box found with id="+box.getId());
		}catch(Exception e){					//Pirmineje versijoje gaude SQLException, ClassNotFoundException (prideta i kompanija Class.forName("com.mysql.jdbc.Driver"))
			e.printStackTrace();
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
			con=dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Box deleted with id="+id);
			}else System.out.println("No box found with id="+id);
		}catch(Exception e){					//Pirmineje versijoje gaude SQLException, ClassNotFoundException (prideta i kompanija Class.forName("com.mysql.jdbc.Driver"))
			e.printStackTrace();
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
		String query = " select id, color, size from boxes";
		List<Box> boxList = new ArrayList<Box>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{			
			con=dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Box box = new Box();
				box.setId(rs.getLong("id"));
				box.setColor(rs.getString("color"));
				box.setSize(rs.getDouble("size"));
				boxList.add(box);
			}
		}catch(Exception e){					//Pirmineje versijoje gaude SQLException, ClassNotFoundException (prideta i kompanija Class.forName("com.mysql.jdbc.Driver"))
			e.printStackTrace();
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
		
}
