package lt.world.app.boxes;

import java.util.List;

public interface BoxService {

	public void addBox(Box box);
	public void updateBox(Box box);
	public List<Box> listBox();
	public Box getBoxById(Long id);
	public void removeBox(Long id);
	
}
