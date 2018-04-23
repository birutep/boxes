package lt.world.app;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lt.world.app.boxes.Box;
import lt.world.app.boxes.BoxService;


@Controller
public class HomeController {
	
	@Autowired
	private BoxService boxService;
	
	//----------------------MODEL ATTRIBUTES---------------------
	@ModelAttribute("listBoxes")
	public List<Box> allBoxes(){
		return this.boxService.listBox();
	}
	
	//----------------------GET ALL------------------------------
	@RequestMapping(value = "/boxes", method = RequestMethod.GET)
	public String listBox(Model model) {
		model.addAttribute("box", new Box());
		return "boxes";
	}
	
	//------------------BOX ADD UPDATE----------------------------
	@RequestMapping(value= "/box/add", method = RequestMethod.POST)
	public String addBox(@ModelAttribute("box") @Valid Box box, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()) {
			return "boxes";
		}
		
		if(box.getId() == null){
			//new box, add it
			this.boxService.addBox(box);
		}else{
			//existing box, call update
			this.boxService.updateBox(box);
		}
		return "redirect:/boxes";
	}
	
	//------------------DELETE----------------------------
	@RequestMapping("/remove/{id}")
    public String removeBox(@PathVariable("id") Long id){
        this.boxService.removeBox(id);
        return "redirect:/boxes";
    }
 
	//------------------EDIT------------------------------
    @RequestMapping("/edit/{id}")
    public String editBox(@PathVariable("id") Long id, Model model){
    	model.addAttribute("box", this.boxService.getBoxById(id));
        return "boxes";
    }
}


