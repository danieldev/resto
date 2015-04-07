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

public class AppetizerFragment extends Fragment {

	ListView productList;
	ProductListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_appetizer, container,
				false);

		List<Product> appetizers = createAppetizers();

		adapter = new ProductListAdapter(getActivity(), appetizers);

		productList = (ListView) view.findViewById(R.id.listview_appetizer);
		productList.setAdapter(adapter);

		return view;
	}

	private List<Product> createAppetizers() {
		List<Product> appetizers = new ArrayList<Product>();
		Product p1 = new Product("Terrine de foie gras", 19.90, "description");
		Product p2 = new Product("Soupe à l'oignon", 4.90, "description");
		Product p3 = new Product("Soupe à l'oignon du Nord", 5.90,
				"description");
		Product p4 = new Product("Taboulé aux herbes au boulgour", 7.50,
				"description");
		Product p5 = new Product("Oeufs cocotte aux herbes", 8.90, "description");

		appetizers.add(p1);
		appetizers.add(p2);
		appetizers.add(p3);
		appetizers.add(p4);
		appetizers.add(p5);

		return appetizers;
	}



}
