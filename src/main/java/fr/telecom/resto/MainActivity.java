package fr.telecom.resto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import fr.telecom.resto.Adapter.OrderListAdapter;
import fr.telecom.resto.Adapter.OrderListAdapter.OnProductRemovedListener;
import fr.telecom.resto.Adapter.ProductListAdapter.OnProductSelectedListener;
import fr.telecom.resto.Adapter.TabsPagerAdapter;
import fr.telecom.resto.Comparator.ComparatorRating;
import fr.telecom.resto.Model.Product;
import fr.telecom.resto.SlidingTab.SlidingTabLayout;


public class MainActivity extends FragmentActivity implements
		OnProductSelectedListener, OnProductRemovedListener {

	// coucou ceci est un essai

	ListView orderList;
	OrderListAdapter adapter;
	ArrayList<Product> products;
	TextView orderSum;

	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

	public static final String PRODUCTS_TAG = "fr.telecom.resto.MainActivity.PRODUCTS";

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

		// to see what people around eat
		Button buttonPeopleAround = (Button) findViewById(R.id.peopleAround);
		buttonPeopleAround.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						PeopleAround.class);
				startActivityForResult(intent, 1);

			}
		});

        // call service
        Button callService = (Button) findViewById(R.id.callWaiter);
        callService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.final_service_toast, Toast.LENGTH_SHORT)
                        .show();

            }
        });

		orderList = (ListView) findViewById(R.id.order_listview);
		products = new ArrayList<Product>();
		adapter = new OrderListAdapter(this, products);
		orderList.setAdapter(adapter);

		orderSum = (TextView) findViewById(R.id.order_sum);
		orderSum.setText("Montant: " + currencyFormatter.format(0));

		Button order = (Button) findViewById(R.id.order_order);
		order.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(MainActivity.this)
						.setTitle(getString(R.string.order_dialog_title))
						.setMessage(
								getString(R.string.order_dialog_text)
										+ "\n\nVotre montant: "
										+ currencyFormatter
												.format(calculateSum(products))
										+ "\n")
						.setPositiveButton(android.R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent(
												MainActivity.this,
												FinalActivity.class);
										intent.putParcelableArrayListExtra(
												MainActivity.PRODUCTS_TAG,
												products);
										startActivity(intent);
									}
								})
						.setNegativeButton(android.R.string.no,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).setIcon(android.R.drawable.ic_dialog_info)
						.show();
			}
		});
	}

	@Override
	public void onProductSelected(Product product) {
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

	@Override
	public void onProductRemoved(Product product) {
		product.setQuantity(0);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ArrayList<Product> addProducts = data.getParcelableArrayListExtra("add");
                for(int i=0;i<addProducts.size();i++){
                    onProductSelected((Product) addProducts.get(i));
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));


            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
            });


        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSortRating:{
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                ((TabsPagerAdapter)viewPager.getAdapter()).update("rating");
                break;
            }
            case R.id.menuSortName:{
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                ((TabsPagerAdapter)viewPager.getAdapter()).update("name");
                break;
            }
            case R.id.menuSortCalorie:{
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                ((TabsPagerAdapter)viewPager.getAdapter()).update("calorie");
                break;
            }
            default:return super.onOptionsItemSelected(item);
        }
        return true;

    }



}

