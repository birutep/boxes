package lt.world.app.boxes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Box {

	private Long id;
	
	@NotNull
	@Min(value=1) @Max(value=500)
	private Double size;
	
	@NotEmpty
	@Size(min=3, max=15)
	@Pattern(regexp = "^([a-zA-Z]+\\s)*[a-zA-Z]+$")
	private String color;	
	
	public Box (double size, String color) {
		this.id=BoxId.getNext();
		this.size=size;
		this.color=color;
	}
	
	public Box() {}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
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

	@Override
	public String toString() {
		return "Box [id=" + id + ", size=" + size + ", color=" + color + "]";
	}

}
