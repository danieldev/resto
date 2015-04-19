package fr.telecom.resto.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.telecom.resto.R;

public class DrinksFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_drinks, container, false);
		return view;
	}
	
    private List<Product> createDrinks() {

        List<Product> drinks = new ArrayList<Product>();

        Product p3 = new Product("Coca Cola", 4, "bouteille 500ml", R.drawable.boisson_coca);

        Product p4 = new Product("Orangina", 4, "bouteille 500ml", R.drawable.boisson_orangina);

        Product p5 = new Product("Bière 1664", 4, "bouteille 330ml", R.drawable.boisson_1664);

        Product p6 = new Product("Eau plate Vittel", 3, "bouteille 1L", R.drawable.boisson_vittel);

        Product p7 = new Product("Eau gazeuse San Pellegrino", 5, "bouteille 1L", R.drawable.boisson_sans_pellegrino);

        Product p8 = new Product("Vin blanc Sancerre", 10, "bouteille 75cl", R.drawable.boisson_sancerre);

        drinks.add(p3);
        drinks.add(p4);
        drinks.add(p5);
        drinks.add(p6);
        drinks.add(p7);
        drinks.add(p8);

        return drinks;
    }

}
