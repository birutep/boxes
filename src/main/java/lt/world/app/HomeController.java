package lt.world.app;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lt.world.app.boxes.Box;
import lt.world.app.boxes.BoxDAO;


@Controller
public class HomeController {
	
	@Autowired
	private BoxDAO boxDAO;

	
//--------------VISU DEZIU PARODYMAS------------------------
	@RequestMapping (value="/table", method=RequestMethod.GET)
	public String showTable(Model model) {
		model.addAttribute("boxes", boxDAO.getAllBoxes());
		return "table";
	}	
	
//---------------DEZES ISTRYNIMAS--------------------------
	
	@RequestMapping (value="/table", method=RequestMethod.POST)
	public String deleteBox(Long id, Model model) {
		boxDAO.deleteBox(id);
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "redirect:table";
	}

//---------------DEZES ADD/UPDATE--------------------------
		@RequestMapping (value="/addUpdateBox", method=RequestMethod.GET)
		public String getPageForAddUpdateBox(Long id, Model model) {
			model.addAttribute("box", new Box());
			if (id == null) {
				model.addAttribute("pageName", "Lets create new Box");
				model.addAttribute("color", null);
				model.addAttribute("size", null);
				model.addAttribute("buttonValue", "Create Box");
				return "boxAddUpdate";
			}
			else {
				Box box = boxDAO.getOneById(id);
				String pageName = "Lets update existing Box " + box.getId();
				model.addAttribute("pageName", pageName);
				model.addAttribute("id", id);
				model.addAttribute("color", box.getColor());
				model.addAttribute("size", box.getSize());
				model.addAttribute("buttonValue", "Update Box");
				return "boxAddUpdate";
			}
			
		}
		
		@RequestMapping (value="/addUpdateBox", method=RequestMethod.POST)
		public String addUpdateBox(@Valid Box box, BindingResult bindingResult, Model model) {
			
			System.out.println("Entering controller method");
			System.out.println("Incoming box:" + box);
			
			if (bindingResult.hasErrors()) {
				if(box.getId() == null) {
					System.out.println("Controller:boxCreation: errors in box creation");
					model.addAttribute("pageName", "Lets create new Box");
					model.addAttribute("id", box.getId());
					model.addAttribute("color", box.getColor());
					model.addAttribute("size", box.getSize());
					model.addAttribute("buttonValue", "Create Box");
				}
				else {
					System.out.println("Controller:boxCreation: errors in box update");
					String pageName = "Lets update existing Box (id " + box.getId() + ")";
					model.addAttribute("pageName", pageName);
					model.addAttribute("id", box.getId());
					model.addAttribute("color", box.getColor());
					model.addAttribute("size", box.getSize());
					model.addAttribute("buttonValue", "Update Box");
				}
				return "boxAddUpdate";
			}
			else if (box.getId() == null) {
				System.out.println("Controller:boxCreation: NO errors, entering creation");
				Box newBox = new Box(box.getSize(),box.getColor());
				boxDAO.addBox(newBox);
			}
			else if (box.getId() != null){
				System.out.println("Controller:boxCreation: NO errors, entering update");
				System.out.println("Entring update");
				boxDAO.updateBox(box);
			}
			System.out.println("Controller:boxCreation: Returning to table view");
			model.addAttribute("boxDAO", boxDAO.getAllBoxes());
			return "redirect:table";					
		}
}

