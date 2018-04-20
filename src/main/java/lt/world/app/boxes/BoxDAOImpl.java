package lt.world.app.boxes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoxDAOImpl implements BoxDAO {

	private List<Box> boxes = new ArrayList<Box>();
	
	@Override
	public void addBox(Box box) {
		boxes.add(new Box (box.getSize(), box.getColor()));	
		System.out.println("Added new box to the array.");
	}

	@Override
	public List<Box> getAllBoxes() {
		return boxes;
	}

	@Override
	public void updateBox(Box box) {
		int iToUpdate = 0;
		
		for (Box item : boxes) {
			if(box.getId() == item.getId()) {
				iToUpdate = boxes.indexOf(item);
			}
		}
		
		boxes.get(iToUpdate).setColor(box.getColor());
		boxes.get(iToUpdate).setSize(box.getSize());
		System.out.println("Update completed");
	}

	@Override
	public void deleteBox (Long id) {
		int i = 0;
		
		for (Box item: boxes) {
			if(item.getId() == id) {
				i = boxes.indexOf(item);
			}
		}
		boxes.remove(i);
		System.out.println("Box deleted");
	}
	
	public void printArray() {
		for (Box box : boxes) {
			System.out.println(box);
		}
	}
	
	public Box getOneById(Long id) {
		Box box = new Box();
		for (Box item : boxes) {
			if(id == item.getId()) {
				box = item;
			}
		}
		return box;
	}
		
}
