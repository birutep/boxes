package lt.world.app.boxes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Box {

//	@NotNull (message="annot: ID is a required field.")
	@Min(value=1, message="annot: Cannot be 0. It is required field.")
	private Integer id;
	
//	@NotNull (message="annot: Size is a required field.")
	@Min(value=1, message="annot: Cannot be smaller than 1") @Max(value=500, message="Cannot be bigger than 500")
	private Double size;
	
	@NotEmpty (message="annot: Color is a required field.")
	@Size(min=3, max=15, message="annot: Must be between 3 to 15 symbols")
	@Pattern(regexp = "^([a-zA-Z]+\\s)*[a-zA-Z]+$", message="annot: Can contain just letters and be of several words.")
	private String color;	
	
	public Box (int id, double size, String color) {
		this.id=id;
		this.size=size;
		this.color=color;
	}
	
	public Box() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	

	

	
	
	
	
}
