package fr.telecom.resto.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Comparator.ComparatorCalorie;
import fr.telecom.resto.Comparator.ComparatorName;
import fr.telecom.resto.Comparator.ComparatorRating;
import fr.telecom.resto.DetailActivity;
import fr.telecom.resto.Model.Product;
import fr.telecom.resto.R;

public class DrinksFragment extends Fragment {

    GridView productGrid;
    ProductListAdapter adapter;
    View view;
    List<Product> drinks = createDrinks();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater
				.inflate(R.layout.fragment_drinks, container, false);

        adapter = new ProductListAdapter(getActivity(), drinks);

        productGrid = (GridView) view.findViewById(R.id.grid_drinks);
        productGrid.setAdapter(adapter);

        productGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectItem(adapter.getItem(position));
            }
        });

        return view;
    }
	
    private List<Product> createDrinks() {

        List<Product> drinks = new ArrayList<Product>();

        Product p3 = new Product("Coca Cola", 4, "bouteille 500ml", R.drawable.boisson_coca);

        Product p4 = new Product("Orangina", 4, "bouteille 500ml", R.drawable.boisson_orangina);

        Product p5 = new Product("Bière 1664", 4, "bouteille 330ml", R.drawable.boisson_1664);

        Product p6 = new Product("Eau plate Vittel", 3, "bouteille 1L", R.drawable.boisson_vittel);

        Product p7 = new Product("Eau gazeuse San Pellegrino", 5, "bouteille 1L", R.drawable.boisson_san_pellegrino);

        Product p8 = new Product("Vin blanc Sancerre", 10, "bouteille 75cl", R.drawable.boisson_sancerre);

        drinks.add(p3);
        drinks.add(p4);
        drinks.add(p5);
        drinks.add(p6);
        drinks.add(p7);
        drinks.add(p8);

        return drinks;
    }

    private void selectItem(Product product) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.PRODUCT_TAG, product);
        getActivity().startActivityForResult(intent, 1);
    }

    public void sort(String sort){
        if (sort.equals("rating")) {
            Collections.sort(this.drinks,new ComparatorRating());
            adapter.notifyDataSetChanged();
        }
        else if (sort.equals("name")) {
            Collections.sort(this.drinks,new ComparatorName());
            adapter.notifyDataSetChanged();
        }
        else if (sort.equals("calorie")) {
            Collections.sort(this.drinks,new ComparatorCalorie());
            adapter.notifyDataSetChanged();
        }
//        switch (sort){
//            case "rating": {
//                Collections.sort(this.drinks, new ComparatorRating());
//                adapter.notifyDataSetChanged();
//                break;
//            }
//            case "name":{
//                Collections.sort(this.drinks,new ComparatorName());
//                adapter.notifyDataSetChanged();
//                break;
//            }
//            case "calorie":{
//                Collections.sort(this.drinks,new ComparatorCalorie());
//                adapter.notifyDataSetChanged();
//                break;
//            }
//            default: return;
//        }
    }

}
