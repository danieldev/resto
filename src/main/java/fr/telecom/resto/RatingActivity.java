package fr.telecom.resto;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import fr.telecom.resto.Adapter.RatingListAdapter;
import fr.telecom.resto.Model.Product;

public class RatingActivity extends Activity {

	private List<Product> products;
	private RatingListAdapter adapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);

		listView = (ListView) findViewById(R.id.rating_listview);

		Bundle bundle = getIntent().getExtras();
		products = bundle.getParcelableArrayList(MainActivity.PRODUCTS_TAG);

		adapter = new RatingListAdapter(this, products);
		listView.setAdapter(adapter);

	}

}
