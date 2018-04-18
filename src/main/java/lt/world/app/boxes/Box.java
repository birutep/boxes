package lt.world.app.boxes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Box {

	@NotNull (message="annot: ID is a required field.")
	@Min(value=1, message="annot: Cannot be 0. It is required field.")
	private int id;
	
	@NotNull (message="annot: Size is a required field.")
	@Min(value=1, message="annot: Cannot be smaller than 1") @Max(value=500, message="Cannot be bigger than 500")
	private double size;
	
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


	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Box [size=" + size + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		return true;
	}

	

	
	
	
	
}
