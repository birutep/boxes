package lt.world.app.boxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoxDAOImpl implements BoxDAO {

	private List<Box> boxes = new ArrayList<Box>(Arrays.asList(
			new Box(1, 1.5, "red"),
			new Box(2, 2.6, "green"),
			new Box(3, 9.3, "blue"))
			);
	
	@Override
	public void addBox(Box box) {
		boxes.add(box);		
	}

	@Override
	public List<Box> getAllBoxes() {
		return boxes;
	}

	@Override
	public void updateBox(Box box) {
		int i = 0;
		for (Box item : boxes) {
			if (item.equals(box)) {
				i = boxes.indexOf(item);
			}
		}
		boxes.remove(i);
		boxes.add(box);
	}

	@Override
	public void deleteBox (int id) {
		int i = 0;
		
		for (Box item: boxes) {
			if(item.getId() == id) {
				i = boxes.indexOf(item);
			}
		}
		boxes.remove(i);
	}
	
	public void printArray() {
		for (Box box : boxes) {
			System.out.println(box);
		}
	}
	
	
		
}
