package lt.world.app.boxes;

import javax.validation.Valid;

public class BoxInputForm {

	private String pageName;
	private String buttonName;
	
	@Valid
	private Box box;
	
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	@Override
	public String toString() {
		return "BoxInputForm [pageName=" + pageName + ", buttonName=" + buttonName + ", box=" + box + "]";
	}
	
	
	
	
}
