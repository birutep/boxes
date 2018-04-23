package lt.world.app.boxes;

import java.util.List;

public interface BoxDAO {

		//Create
		public void save(Box box);
		//Read
		public Box getById(Long id);
		//Update
		public void update(Box box);
		//Delete
		public void deleteById(Long id);
		//Get All
		public List<Box> getAll();
		
}