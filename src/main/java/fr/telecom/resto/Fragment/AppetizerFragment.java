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

		Product p = new Product("Misto di verdure", 9.90, "Roquette, artichaut, tomates cerise marinées",
				R.drawable.misto_di_verdure);
        p.setRating(3);
        p.setCalorie(200);
        p.getComments().add("Délicieux !");
        p.getComments().add("Excellent");
        p.getComments().add("Bof");

        appetizers.add(p);

        p = new Product("Tiramisú primavera", 10.90, "Tiramisú salé au grana padano, poivrons marinés, crème de baslamique",
                R.drawable.tiramisu_primavera);
        p.setRating(4);
        p.setCalorie(300);
        p.getComments().add("Excellent, meilleur que le tiramisu sucré !");
        p.getComments().add("Je n'ai pas trop aimé");

        appetizers.add(p);

        p = new Product("La véritable burrata", 8.90, "Burrata (mozzarella au cœur crémeux), huile d’olive et basilic, roquette, poivrons marinés, crème de balsamique",
                R.drawable.burrata);
        p.setRating(2);
        p.setCalorie(350);
        p.getComments().add("Moyen");
        p.getComments().add("Pas terrible");
        p.getComments().add("Délicieux");

        appetizers.add(p);

        p = new Product("Chèvre chaud à l’italienne", 10.50, "Tartines de pain ciabatta aux olives agrémentées de crème ricotta aux herbes, mozzarella et fromage de chèvre, salade de saison, copeaux de fromage italien",
                R.drawable.chevre_chaud);
        p.setRating(4);
        p.setCalorie(300);
        p.getComments().add("Très bon !");
        p.getComments().add("Excellent !");

        appetizers.add(p);

        p = new Product("Carpaccio de bœuf", 11.90, "Mariné à l’huile d’olive et basilic, copeaux de fromage italien",
                R.drawable.carpaccio);
        p.setRating(4);
        p.setCalorie(300);
        p.getComments().add("Excellent !");

        appetizers.add(p);

        p = new Product("Insalata del mare", 10.90, "Gamberi (gambas) et petites noix de Saint-Jacques marinées aux épices citronnées et poêlées, salade de saison, roquette, tomates cerise marinées, pains Del Arte, crème ricotta aux herbes, saumon fumé, citron",
                R.drawable.insalata);
        p.setRating(3);
        p.setCalorie(150);
        p.getComments().add("Bon et léger");
        p.getComments().add("Pas terrible");

        appetizers.add(p);

//        Product p2 = new Product("Soupe à l'oignon", 4.90, "(Description)",
//				R.drawable.entree_soupe);
//        p2.setRating(4);
//        p2.setCalorie(250);
//
//		Product p3 = new Product("Soupe à l'oignon du Nord", 5.90,
//				"(Description)", R.drawable.entree_soupe_nord);
//        p3.setRating(4);
//        p3.setCalorie(150);
//
//		Product p4 = new Product("Taboulé aux herbes au boulgour", 7.50,
//				"(Description)", R.drawable.entree_taboule);
//        p4.setRating(2);
//        p4.setCalorie(300);
//
//		Product p5 = new Product("Oeufs cocotte aux herbes", 8.90,
//				"(Description)", R.drawable.entree_oeufs);
//        p5.setRating(5);
//        p5.setCalorie(280);
//
//		appetizers.add(p2);
//		appetizers.add(p3);
//		appetizers.add(p4);
//		appetizers.add(p5);

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
