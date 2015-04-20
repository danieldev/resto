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

public class DessertFragment extends Fragment {

    GridView productGrid;
    ProductListAdapter adapter;
    View view;
    List<Product> desserts = createDesserts();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dessert, container, false);

        adapter = new ProductListAdapter(getActivity(), desserts);

        productGrid = (GridView) view.findViewById(R.id.grid_dessert);
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

    private List<Product> createDesserts() {
        List<Product> desserts = new ArrayList<Product>();

        Product p = new Product("Panna cotta", 9.90, "La vraie crème à l’italienne à agrémenter de coulis de framboise, lait caramélisé ou sauce au chocolat",
                R.drawable.pannacotta);
        p.setRating(3);
        p.setCalorie(100);
        p.getComments().add("Délicieux !");
        p.getComments().add("Excellent");
        p.getComments().add("Bof");

        desserts.add(p);

        p = new Product("Tiramisú", 10.90, "La vraie recette avec le mascarpone, le café, le Marsala et ses biscuits",
                R.drawable.tiramisu);
        p.setRating(4);
        p.setCalorie(300);
        p.getComments().add("Excellent, meilleur que le tiramisu salé !");
        p.getComments().add("Je n'ai pas trop aimé");

        desserts.add(p);

        p = new Product("Tarte citron meringuée", 8.90, "",
                R.drawable.tartecitron);
        p.setRating(4);
        p.setCalorie(350);
        p.getComments().add("Moyen");
        p.getComments().add("Pas terrible");
        p.getComments().add("Délicieux");

        desserts.add(p);

        p = new Product("Crème brûlée", 10.50, "",
                R.drawable.creme_brulee);
        p.setRating(4);
        p.setCalorie(120);
        p.getComments().add("Très bon !");
        p.getComments().add("Excellent !");

        desserts.add(p);

        p = new Product("Pommes all’ Amaretto", 11.90, "Pommes Amaretto, crumble et glace vanille",
                R.drawable.pomme);
        p.setRating(4);
        p.setCalorie(140);
        p.getComments().add("Excellent !");

        desserts.add(p);

        p = new Product("Fondant au chocolat", 10.90, "Servi avec un coulis de framboise",
                R.drawable.fondant);
        p.setRating(3);
        p.setCalorie(150);
        p.getComments().add("Pas terrible");

        desserts.add(p);

        return desserts;
    }

    private void selectItem(Product product) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.PRODUCT_TAG, product);
        getActivity().startActivityForResult(intent, 1);
    }

    public void sort(String sort){
        if (sort.equals("rating")) {
            Collections.sort(this.desserts, new ComparatorRating());
            adapter.notifyDataSetChanged();
        }
        else if (sort.equals("name")) {
            Collections.sort(this.desserts,new ComparatorName());
            adapter.notifyDataSetChanged();
        }
        else if (sort.equals("calorie")) {
            Collections.sort(this.desserts,new ComparatorCalorie());
            adapter.notifyDataSetChanged();
        }

    }

}
