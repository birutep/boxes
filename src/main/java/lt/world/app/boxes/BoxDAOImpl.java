package lt.world.app.boxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoxDAOImpl implements BoxDAO {

	private List<Box> boxes = new ArrayList<Box>();
	
	@Override
	public void addBox(Box box) {
		boxes.add(box);	
		System.out.println("Added new box to the array.");
	}

	@Override
	public List<Box> getAllBoxes() {
		return boxes;
	}

	@Override
	public void updateBox(Box box) {
		Long idToDelete = box.getId();
		
		int iToDelete = 0;
		for (Box item : boxes) {
			if(idToDelete == item.getId()) {
				iToDelete = boxes.indexOf(item);
			}
		}
		
		boxes.remove(iToDelete);
		boxes.add(iToDelete,box);
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
