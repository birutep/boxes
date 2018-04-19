package lt.world.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lt.world.app.boxes.Box;
import lt.world.app.boxes.BoxDAO;
import lt.world.app.users.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private BoxDAO boxDAO;

	
//--------------VISU DEZIU PARODYMAS------------------------
	@RequestMapping (value="/table", method=RequestMethod.GET)
	public String showTable(Model model) {
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "table";
	}	
	
//---------------DEZES ISTRYNIMAS--------------------------
	
	@RequestMapping (value="/table", method=RequestMethod.POST)
	public String deleteBox(Long id, Model model) {
		boxDAO.deleteBox(id);
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "table";
	}

//---------------DEZES ADD/UPDATE--------------------------
		@RequestMapping (value="/addUpdateBox", method=RequestMethod.GET)
		public String getPageForBoxCreation(Long id, Model model) {
			model.addAttribute("box", new Box());
			if (id == null) {
				model.addAttribute("pageName", "Lets create new Box");
				model.addAttribute("id", null);
				model.addAttribute("color", null);
				model.addAttribute("size", null);
				model.addAttribute("buttonValue", "Create Box");
				return "boxAddUpdate";
			}
			else {
				Box box = boxDAO.getOneById(id);
				model.addAttribute("pageName", "Lets update existing Box");
				model.addAttribute("id", box.getId());
				model.addAttribute("color", box.getColor());
				model.addAttribute("size", box.getSize());
				model.addAttribute("buttonValue", "Update Box");
				return "boxAddUpdate";
			}
			
		}
		
		@RequestMapping (value="/addUpdateBox", method=RequestMethod.POST)
		public String boxCreation(String buttonValue, @Valid Box box, BindingResult bindingResult, Model model) {
			System.out.println("Entering controller method");
			System.out.println("utton value: " + buttonValue);
			if (bindingResult.hasErrors()) {
				if(buttonValue.equals("Create Box")) {
					System.out.println("Controller:boxCreation: errors in box creation");
					model.addAttribute("box", new Box());		//SITAS BUTINAS< ANTRAIP NET NEPAKRAUS PUSLAPIO< NES NEZINOS, KOKIO MODELIO LAUKTI JAM
					model.addAttribute("pageName", "Lets create new Box");
					model.addAttribute("id", null);
					model.addAttribute("color", null);
					model.addAttribute("size", null);
					model.addAttribute("buttonValue", "Create Box");
				}
				else {
					System.out.println("Controller:boxCreation: errors in box update");
					model.addAttribute("pageName", "Lets update existing Box");
					model.addAttribute("id", box.getId());
					model.addAttribute("color", box.getColor());
					model.addAttribute("size", box.getSize());
					model.addAttribute("buttonValue", "Update Box");
				}
				return "boxAddUpdate";
			}
			else if (buttonValue.equals("Create Box")) {
				System.out.println("Controller:boxCreation: NO errors, entering creation");
				boxDAO.addBox(box);
			}
			else if (buttonValue.equals("Update Box")){
				System.out.println("Controller:boxCreation: NO errors, entering update");
				System.out.println("Entring update");
				boxDAO.updateBox(box);
			}
			System.out.println("Controller:boxCreation: Returning to table view");
			model.addAttribute("boxDAO", boxDAO.getAllBoxes());
			return "table";						//CIA KAZKOKS GRYBAS< REIK VEL KAD MAN GRAZINTU IR I TABLE LINKUTI
		}

	
}

