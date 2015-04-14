package fr.telecom.resto;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

import fr.telecom.resto.Model.Product;

public class DetailActivity extends Activity {

	public static final String PRODUCT_TAG = "fr.telecom.resto.DetailActivity.PRODUCT";

	
	
	private Product product;
    ListView list;
    ArrayList<String> c=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Bundle bundle = getIntent().getExtras();
		product = bundle.getParcelable(PRODUCT_TAG);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

		TextView name = (TextView) findViewById(R.id.detail_product_name);
		name.setText(product.getName());

        TextView description = (TextView) findViewById(R.id.detail_product_description);
        description.setText(product.getDescription());

        TextView price = (TextView) findViewById(R.id.detail_product_price);
        price.setText(currencyFormatter.format(product.getPrice()));

        RatingBar rating = (RatingBar) findViewById(R.id.detail_product_rating);
        rating.setRating(product.getRating());

		ImageView image = (ImageView) findViewById(R.id.detail_product_image);
		image.setImageDrawable(getResources().getDrawable(
				product.getImage()));

        list = (ListView) findViewById(R.id.listComments);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,product.getComments());
        list.setAdapter(adapter1);


		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
