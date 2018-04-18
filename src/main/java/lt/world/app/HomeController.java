package lt.world.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
	public String login (@Validated User user, Model model) {
		model.addAttribute("userName", user.getUserName());
		return "user";
	}	
	
//--------------VISU DEZIU PARODYMAS------------------------
	@RequestMapping (value="/table", method=RequestMethod.GET)
	public String showTable(Model model) {
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "table";
	}	
	
//---------------DEZES PRIDEJIMAS--------------------------
	@RequestMapping (value="/boxCreationInput", method=RequestMethod.GET)
	public String boxCreationPage() {
		return "boxCreationInput";
	}
	
	@RequestMapping (value="/boxCreationResult", method=RequestMethod.POST)
	public String createBox(@Validated Box box, Model model) {
		boxDAO.addBox(box);
		model.addAttribute("id", box.getId());
		model.addAttribute("color", box.getColor());
		model.addAttribute("size", box.getSize());
		return "boxCreationResult";
	}

//---------------DEZES ISTRYNIMAS--------------------------
	
	@RequestMapping (value="/table", method=RequestMethod.POST)
	public String deleteBox(int id, Model model) {
		boxDAO.deleteBox(id);
		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
		return "table";
		//ar cia, jei noriu, kad griztu i linka "tables"reiktu kazkoki redirecta daryti?
		//ar cia gerai taip daryti su DELETE
	}
	

//---------------DEZES ATNAUJINIMAS--------------------------
	
	@RequestMapping (value="/boxUpdateInput", method=RequestMethod.GET)
	public String boxUpdateInput(int id, Model model) {
		model.addAttribute("id", id);
		return "boxUpdateInput";
	}
	
	@RequestMapping (value="/boxUpdateResult", method=RequestMethod.POST)
	public String boxUpdateResult(@Validated Box box, Model model) {
		boxDAO.updateBox(box);
		model.addAttribute("color", box.getColor());
		model.addAttribute("size", box.getSize());
		return "boxUpdateResult";
	}
	
}

