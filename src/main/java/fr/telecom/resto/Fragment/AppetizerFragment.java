package fr.telecom.resto.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import fr.telecom.resto.DetailActivity;
import fr.telecom.resto.R;
import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Model.Product;

public class AppetizerFragment extends Fragment {

	GridView productGrid;
	ProductListAdapter adapter;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_appetizer, container, false);

		List<Product> appetizers = createAppetizers();

		adapter = new ProductListAdapter(getActivity(), appetizers);

		productGrid = (GridView) view.findViewById(R.id.grid_appetizer);
		productGrid.setAdapter(adapter);

		productGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(adapter.getItem(position));
			}
		});

		return view;
	}

	private List<Product> createAppetizers() {
		List<Product> appetizers = new ArrayList<Product>();
		Product p1 = new Product("Terrine de foie gras", 19.90, "description",
				R.drawable.entree_foie_gras);
		Product p2 = new Product("Soupe à l'oignon", 4.90, "description",
				R.drawable.entree_soupe);
		Product p3 = new Product("Soupe à l'oignon du Nord", 5.90,
				"description", R.drawable.entree_soupe_nord);
		Product p4 = new Product("Taboulé aux herbes au boulgour", 7.50,
				"description", R.drawable.entree_taboule);
		Product p5 = new Product("Oeufs cocotte aux herbes", 8.90,
				"description", R.drawable.entree_oeufs);

		appetizers.add(p1);
		appetizers.add(p2);
		appetizers.add(p3);
		appetizers.add(p4);
		appetizers.add(p5);

		return appetizers;
	}

	private void selectItem(Product product) {
		Intent intent = new Intent(view.getContext(), DetailActivity.class);
		intent.putExtra(DetailActivity.PRODUCT_TAG, product);
		startActivity(intent);
	}

}
