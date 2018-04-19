package lt.world.app.boxes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxServiceImpl implements BoxService{

	@Autowired
	private BoxDAO boxDAO;

	
	@Override
	public void addBox(Box box) {
		this.boxDAO.addBox(box);
	}

	@Override
	public void updateBox(Box box) {
		this.boxDAO.updateBox(box);
	}

	@Override
	public List<Box> listBox() {
		return this.boxDAO.getAllBoxes();
	}

	@Override
	public Box getBoxById(Long id) {
		return this.boxDAO.getOneById(id);
	}

	@Override
	public void removeBox(Long id) {
		this.boxDAO.deleteBox(id);
		
	}

	
	
}
