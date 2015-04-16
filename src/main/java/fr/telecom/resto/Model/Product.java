package fr.telecom.resto.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import fr.telecom.resto.R;

public class Product implements Parcelable{

	private String name;

	private double price;

	private String description;

    private int rating;

	private int image;

    private List<String> comments=new ArrayList<String>();


	// The selected quantity in the current order
	private int quantity;

	// The sum of the order of this product (depending on the quantity)
	private double sum;

	public Product(String name, double price, String description,int image) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.setImage(image);
        this.rating= 0;
		quantity = 0;
		sum = 0;
	}

	public Product(Parcel source) {
		readFromParcel(source);
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public List<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

	public boolean equals(Product product) {
		if (getName().equals(product.name) && getPrice() == product.price) {
			return true;
		}
		return false;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeDouble(this.price);
		dest.writeString(this.description);
        dest.writeInt(this.rating);
		dest.writeInt(this.image);
        dest.writeStringList(this.comments);
	}

	public void readFromParcel(Parcel source) {
		name = source.readString();
		price = source.readDouble();
		description = source.readString();
        rating=source.readInt();
		image = source.readInt();
        comments=source.createStringArrayList();

	}

	public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
		public Product createFromParcel(Parcel in) {
			return new Product(in);
		}

		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

}
