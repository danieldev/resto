package fr.telecom.resto.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.telecom.resto.R;
import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Model.Product;

public class MainDishFragment extends Fragment {

	ListView productList;
	ProductListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main_dish, container,
				false);

		List<Product> mainDishes = createMainDishes();

		adapter = new ProductListAdapter(getActivity(), mainDishes);

		productList = (ListView) view.findViewById(R.id.listview_main_dish);
		productList.setAdapter(adapter);

		return view;
	}

	private List<Product> createMainDishes() {
		List<Product> appetizers = new ArrayList<Product>();
		Product p1 = new Product("Coq au vin maison", 14.90, "description",
				R.drawable.main_dish_coq);
		Product p2 = new Product("Rôti de boeuf à l'oignon caramélisé", 18.90, "description",
				R.drawable.main_dish_boeuf);

		appetizers.add(p1);
		appetizers.add(p2);

		return appetizers;
	}


}
