package lt.world.app.boxes;

import java.util.List;

public interface BoxDAO {

	//Create
	public void addBox(Box box);
	
	//Read
	public List<Box> getAllBoxes();
	
	//Update
	public void updateBox (Box box);
	
	//Delete
	public void deleteBox(Integer i);
	
	//print array
	public void printArray();
	
	public Box getOneById(Integer id);
}
