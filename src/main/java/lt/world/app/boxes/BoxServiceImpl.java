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
		this.boxDAO.save(box);
	}

	@Override
	public void updateBox(Box box) {
		this.boxDAO.update(box);
	}

	@Override
	public List<Box> listBox() {
		return this.boxDAO.getAll();
	}

	@Override
	public Box getBoxById(Long id) {
		return this.boxDAO.getById(id);
	}

	@Override
	public void removeBox(Long id) {
		this.boxDAO.deleteById(id);
		
	}

	
	
}
