package fr.telecom.resto.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import fr.telecom.resto.Adapter.TabsPagerAdapter;
import fr.telecom.resto.Comparator.ComparatorCalorie;
import fr.telecom.resto.Comparator.ComparatorName;
import fr.telecom.resto.Comparator.ComparatorRating;
import fr.telecom.resto.DetailActivity;
import fr.telecom.resto.R;
import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Model.Product;

public class AppetizerFragment extends Fragment {

	GridView productGrid;
	ProductListAdapter adapter;
	View view;
    List<Product> appetizers = createAppetizers();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_appetizer, container, false);

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
		Product p1 = new Product("Terrine de foie gras", 19.90, "Le foie gras est une spécialité culinaire obtenue après l'engraissement des foies d'oies et de canards.",
				R.drawable.entree_foie_gras);
        p1.setRating(3);
        p1.setCalorie(200);
        p1.getComments().add("Very Good");
        p1.getComments().add("Excellent!");

		Product p2 = new Product("Soupe à l'oignon", 4.90, "(Description)",
				R.drawable.entree_soupe);
        p2.setRating(4);
        p2.setCalorie(250);

		Product p3 = new Product("Soupe à l'oignon du Nord", 5.90,
				"(Description)", R.drawable.entree_soupe_nord);
        p3.setRating(4);
        p3.setCalorie(150);

		Product p4 = new Product("Taboulé aux herbes au boulgour", 7.50,
				"(Description)", R.drawable.entree_taboule);
        p4.setRating(2);
        p4.setCalorie(300);

		Product p5 = new Product("Oeufs cocotte aux herbes", 8.90,
				"(Description)", R.drawable.entree_oeufs);
        p5.setRating(5);
        p5.setCalorie(280);

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
		getActivity().startActivityForResult(intent, 1);
	}

    public void sort(String sort){
        if (sort.equals("rating")) {
            Collections.sort(this.appetizers,new ComparatorRating());
            adapter.notifyDataSetChanged();
        }
        else if (sort.equals("name")) {
            Collections.sort(this.appetizers,new ComparatorName());
            adapter.notifyDataSetChanged();
        }
        else if (sort.equals("calorie")) {
            Collections.sort(this.appetizers,new ComparatorCalorie());
            adapter.notifyDataSetChanged();
        }
//        switch (sort){
//            case "rating": {
//                Collections.sort(this.appetizers,new ComparatorRating());
//                adapter.notifyDataSetChanged();
//                break;
//            }
//            case "name":{
//                Collections.sort(this.appetizers,new ComparatorName());
//                adapter.notifyDataSetChanged();
//                break;
//            }
//            case "calorie":{
//                Collections.sort(this.appetizers,new ComparatorCalorie());
//                adapter.notifyDataSetChanged();
//                break;
//            }
//            default: return;
//        }
    }




}
