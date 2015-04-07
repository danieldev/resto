package fr.telecom.resto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import fr.telecom.resto.Adapter.OrderListAdapter;
import fr.telecom.resto.Adapter.ProductListAdapter.OnProductSelectedListener;
import fr.telecom.resto.Adapter.TabsPagerAdapter;
import fr.telecom.resto.Model.Product;
import fr.telecom.resto.SlidingTab.SlidingTabLayout;

public class MainActivity extends FragmentActivity implements
		OnProductSelectedListener {

	ListView orderList;
	OrderListAdapter adapter;
	ArrayList<Product> products;
	TextView orderSum;

	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get the ViewPager and set it's PagerAdapter so that it can display
		// items
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager(),
				MainActivity.this));

		SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
		// Center the tabs in the layout
		slidingTabLayout.setDistributeEvenly(true);
		slidingTabLayout.setViewPager(viewPager);

		orderList = (ListView) findViewById(R.id.order_listview);
		products = new ArrayList<Product>();
		adapter = new OrderListAdapter(this, products);
		orderList.setAdapter(adapter);

		orderSum = (TextView) findViewById(R.id.order_sum);
		orderSum.setText("Montant: " + currencyFormatter.format(0));
	}

	@Override
	public void onProductSelected(Product product) {
		Toast.makeText(this, "Product: " + product.getName(),
				Toast.LENGTH_SHORT).show();
		for (Product p : products) {
			if (product.equals(p)) {
				p.setQuantity(p.getQuantity() + 1);
				product.setSum(product.getPrice() * p.getQuantity());
				adapter.notifyDataSetChanged();
				orderSum.setText("Montant: "
						+ currencyFormatter.format(calculateSum(products)));
				return;
			}
		}
		product.setQuantity(product.getQuantity() + 1);
		product.setSum(product.getPrice());
		adapter.add(product);
		adapter.notifyDataSetChanged();
		orderSum.setText("Montant: "
				+ currencyFormatter.format(calculateSum(products)));
	}

	private double calculateSum(List<Product> products) {
		double sum = 0;
		for (Product product : products) {
			sum += product.getSum();
		}
		return sum;
	}
}
