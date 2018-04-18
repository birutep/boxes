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
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BoxDAO boxDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping (value="/login", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
		
	@RequestMapping (value="/user", method=RequestMethod.POST)
	public String login (@Valid User user, Model model) {		//CIA DAR TRUKSTA USERIUI KOKIA NORS VALIDACIJA UZDETI (PVZ KAD BUTU KOKS NORS VARDAS BE SKAICIU)
		model.addAttribute("userName", user.getUserName());
		return "user";
	}	
	
//--------------VISU DEZIU PARODYMAS------------------------
	@RequestMapping (value="/table", method=RequestMethod.GET)
	public String showTable(Model model) {
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "table";
	}	
	
//---------------DEZES ISTRYNIMAS--------------------------
	
	@RequestMapping (value="/table", method=RequestMethod.POST)
	public String deleteBox(int id, Model model) {
		boxDAO.deleteBox(id);
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "table";
	}
	
//---------------DEZES PRIDEJIMAS--------------------------
	@RequestMapping (value="/boxCreation", method=RequestMethod.GET)
	public String boxCreationPage(Model model) {
		model.addAttribute("box", new Box());
		return "boxCreation";
	}
	
	@RequestMapping (value="/boxCreation", method=RequestMethod.POST)
	public String createBox(@Valid Box box, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "boxCreation";
		}
		
		boxDAO.addBox(box);
		model.addAttribute("id", box.getId());
		model.addAttribute("color", box.getColor());
		model.addAttribute("size", box.getSize());
		return "boxCreationResult";
	}
	

//---------------DEZES ATNAUJINIMAS--------------------------
	
	@RequestMapping (value="/boxUpdate", method=RequestMethod.GET)
	public String boxUpdateInput(int id, Model model) {
		model.addAttribute("box", new Box());			//SITAS BUTINAS< ANTRAIP NET NEPAKRAUS PUSLAPIO< NES NEZINOS, KOKIO MODELIO LAUKTI JAM
		model.addAttribute("id", id);
		return "boxUpdate";
	}
	
	@RequestMapping (value="/boxUpdate", method=RequestMethod.POST)
	public String boxUpdateResult(@Valid Box box, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("id", box.getId()); 
			return "boxUpdate";
		}
		boxDAO.updateBox(box);
		model.addAttribute("id", box.getId());
		model.addAttribute("color", box.getColor());
		model.addAttribute("size", box.getSize());
		return "boxUpdateResult";
	}
	
}

