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
	public String addBox(@ModelAttribute("box") @Valid Box box){
		
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
	
	
	
//	
//	
////--------------VISU DEZIU PARODYMAS------------------------
//	@RequestMapping (value="/table", method=RequestMethod.GET)
//	public String showTable(Model model) {
//		model.addAttribute("boxes", boxDAO.getAllBoxes());
//		return "table";
//	}	
//	
////---------------DEZES ISTRYNIMAS--------------------------
//	
//	@RequestMapping (value="/table", method=RequestMethod.POST)
//	public String deleteBox(Long id, Model model) {
//		boxDAO.deleteBox(id);
//		model.addAttribute("boxDAO", boxDAO.getAllBoxes());
//		return "redirect:table";
//	}
//
////---------------DEZES ADD/UPDATE--------------------------
//		@RequestMapping (value="/addUpdateBox", method=RequestMethod.GET)
//		public String getPageForAddUpdateBox(Long id, Model model) {
//			
//			BoxInputForm boxInputForm = new BoxInputForm();
//			boxInputForm.setPageName("Lets create new Box");
//			boxInputForm.setButtonName("Create Box");
//			
//			model.addAttribute("boxInputForm", boxInputForm);
//			return "boxAddUpdate";
////			if (id != null) {
////				
////				Box box = boxDAO.getOneById(id);
////				String pageName = "Lets update existing Box (id " + box.getId() + ")";
////				model.addAttribute("pageName", pageName);
////				model.addAttribute("id", id);
////				model.addAttribute("color", box.getColor());
////				model.addAttribute("size", box.getSize());
////				model.addAttribute("buttonName", "Update Box");
////				return "boxAddUpdate";
////			}			
//		}
//		
//		@RequestMapping (value="/addUpdateBox", method=RequestMethod.POST)
//		public String addUpdateBox(@Valid BoxInputForm boxInputForm, BindingResult bindingResult, Model model) {  			
//			System.out.println("Entering controller method");
//			System.out.println("Incoming box:" + boxInputForm.getBox());
//			
////			Map<String, Object> attributesMap = model.asMap();
////			System.out.println(attributesMap);
//			
//			if (bindingResult.hasErrors()) {	
////				model.addAllAttributes(attributesMap);
////				System.out.println(model);
//				return "boxAddUpdate";
//				
////				if (boxInputForm.getBox().getId() == null) {
////					model.addAttribute("pageName", "Lets create new Box");
////					model.addAttribute("color", null);
////					model.addAttribute("size", null);
////					model.addAttribute("buttonName", "Create Box");
////					return "boxAddUpdate";
////				}
////				else {
////					Box box = boxDAO.getOneById(boxInputForm.getBox().getId());
////					String pageName = "Lets update existing Box (id " + box.getId() + ")";
////					model.addAttribute("pageName", pageName);
////					model.addAttribute("id", boxInputForm.getBox().getId());
////					model.addAttribute("color", box.getColor());
////					model.addAttribute("size", box.getSize());
////					model.addAttribute("buttonName", "Update Box");
////					return "boxAddUpdate";
////				}			
//				
//				
//			}
//			else if (boxInputForm.getBox().getId() == null) {
//				System.out.println("Controller:boxCreation: NO errors, entering creation");
//				Box newBox = new Box(boxInputForm.getBox().getSize(),boxInputForm.getBox().getColor());
//				boxDAO.addBox(newBox);
//			}
//			else if (boxInputForm.getBox().getId() != null){
//				System.out.println("Controller:boxCreation: NO errors, entering update");
//				System.out.println("Entring update");
//				boxDAO.updateBox(boxInputForm.getBox());
//			}
//			System.out.println("Controller:boxCreation: Returning to table view");
//			model.addAttribute("boxDAO", boxDAO.getAllBoxes());
//			return "redirect:table";					
//		}
//}

