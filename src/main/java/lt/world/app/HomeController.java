package lt.world.app;

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
	

	@RequestMapping(value = "/boxes", method = RequestMethod.GET)
	public String listBox(Model model) {
		model.addAttribute("box", new Box());
		model.addAttribute("listBoxes", this.boxService.listBox());
		return "boxes";
	}
	
	//For add and update box both
	@RequestMapping(value= "/box/add", method = RequestMethod.POST)
	public String addBox(@ModelAttribute("box") @Valid Box box, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("listBoxes", this.boxService.listBox()); 
			return "boxes";
		}
		
//		model.addAttribute("box", box);
//		model.addAttribute("listBoxes", this.boxService.listBox());
		
			if(box.getId() == null){
				System.out.println("Controller: add NEW");
				//new box, add it
				this.boxService.addBox(box);
			}else{
				//existing box, call update
				this.boxService.updateBox(box);
			}
		
		return "redirect:/boxes";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeBox(@PathVariable("id") Long id){
		
        this.boxService.removeBox(id);
        return "redirect:/boxes";
    }
 
    @RequestMapping("/edit/{id}")
    public String editBox(@PathVariable("id") Long id, Model model){
        model.addAttribute("box", this.boxService.getBoxById(id));
        model.addAttribute("listBoxes", this.boxService.listBox());
        return "boxes";
    }
}


