package fr.telecom.resto;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fr.telecom.resto.Model.Product;

public class FinalActivity extends Activity {

	ArrayList<Product> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);

		Bundle bundle = getIntent().getExtras();
		products = bundle.getParcelableArrayList(MainActivity.PRODUCTS_TAG);

		ImageView finalMenu = (ImageView) findViewById(R.id.final_menu);

		Picasso.with(this).load(R.drawable.menu).centerCrop().fit()
				.into(finalMenu);

		ImageView finalWater = (ImageView) findViewById(R.id.final_water);

		Picasso.with(this).load(R.drawable.water).fit()
				.into(finalWater);
		
		ImageView finalService = (ImageView) findViewById(R.id.final_service);

		Picasso.with(this).load(R.drawable.service).centerCrop().fit()
				.into(finalService);
		
		ImageView final_rating = (ImageView) findViewById(R.id.final_rating);

		Picasso.with(this).load(R.drawable.star).fit()
				.into(final_rating);

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
