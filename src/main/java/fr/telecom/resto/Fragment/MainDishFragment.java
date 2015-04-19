package fr.telecom.resto.Fragment;

import java.util.ArrayList;
import java.util.Collections;
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

import fr.telecom.resto.Comparator.ComparatorCalorie;
import fr.telecom.resto.Comparator.ComparatorName;
import fr.telecom.resto.Comparator.ComparatorRating;
import fr.telecom.resto.DetailActivity;
import fr.telecom.resto.R;
import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Model.Product;

public class MainDishFragment extends Fragment {

	GridView productGrid;
	ProductListAdapter adapter;
	View view;
    List<Product> mainDishes = createMainDishes();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_main_dish, container,
				false);

		adapter = new ProductListAdapter(getActivity(), mainDishes);

		productGrid = (GridView) view.findViewById(R.id.grid_main_dish);
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

	private List<Product> createMainDishes() {
		List<Product> appetizers = new ArrayList<Product>();
		Product p1 = new Product("Coq au vin maison", 14.90, "description",
				R.drawable.main_dish_coq);
		Product p2 = new Product("Rôti de boeuf à l'oignon caramélisé", 18.90,
				"description", R.drawable.main_dish_boeuf);

		appetizers.add(p1);
		appetizers.add(p2);

		return appetizers;
	}

	private void selectItem(Product product) {
		Intent intent = new Intent(view.getContext(), DetailActivity.class);
		intent.putExtra(DetailActivity.PRODUCT_TAG, product);
		startActivityForResult(intent,1);
	}

    public void sort(String sort){
        switch (sort){
            case "rating": {
                Collections.sort(this.mainDishes, new ComparatorRating());
                adapter.notifyDataSetChanged();
            }
            case "name":{
                Collections.sort(this.mainDishes,new ComparatorName());
                adapter.notifyDataSetChanged();
            }
            case "calorie":{
                Collections.sort(this.mainDishes,new ComparatorCalorie());
                adapter.notifyDataSetChanged();
            }
            default: return;
        }
    }
}
