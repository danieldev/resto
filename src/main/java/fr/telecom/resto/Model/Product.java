package fr.telecom.resto.Model;

public class Product {

	private String name;

	private double price;

	private String description;

	private int image;

	// The selected quantity in the current order
	private int quantity;

	// The sum of the order of this product (depending on the quantity)
	private double sum;

	public Product(String name, double price, String description, int image) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.setImage(image);
		quantity = 0;
		sum = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public boolean equals(Product product) {
		if (getName().equals(product.name) && getPrice() == product.price) {
			return true;
		}
		return false;
	}

}
