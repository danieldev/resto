package fr.telecom.resto;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import fr.telecom.resto.Model.Product;

public class DetailActivity extends Activity {

	public static final String PRODUCT_TAG = "fr.telecom.resto.DetailActivity.PRODUCT";

	private Product product;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Bundle bundle = getIntent().getExtras();
		product = bundle.getParcelable(PRODUCT_TAG);

		TextView name = (TextView) findViewById(R.id.detail_product_name);
		name.setText(product.getName());

		ImageView image = (ImageView) findViewById(R.id.detail_product_image);
		image.setImageDrawable(getResources().getDrawable(
				product.getImage()));

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
