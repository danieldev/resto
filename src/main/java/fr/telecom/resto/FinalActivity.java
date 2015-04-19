package fr.telecom.resto;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import fr.telecom.resto.Model.Product;

public class FinalActivity extends Activity {

	ArrayList<Product> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);

		Bundle bundle = getIntent().getExtras();
		products = bundle.getParcelableArrayList(MainActivity.PRODUCTS_TAG);

		LinearLayout service = (LinearLayout) findViewById(R.id.final_service_layout);
		service.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(FinalActivity.this,
						R.string.final_service_toast, Toast.LENGTH_SHORT)
						.show();
			}
		});

		LinearLayout water = (LinearLayout) findViewById(R.id.final_water_layout);
		water.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(FinalActivity.this, R.string.final_water_toast,
						Toast.LENGTH_SHORT).show();
			}
		});

		LinearLayout menu = (LinearLayout) findViewById(R.id.final_menu_layout);
		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FinalActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});

		LinearLayout rating = (LinearLayout) findViewById(R.id.final_rating_layout);
		rating.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FinalActivity.this,
						RatingActivity.class);
				intent.putParcelableArrayListExtra(MainActivity.PRODUCTS_TAG,
						products);
				startActivity(intent);
			}
		});

	}

}
