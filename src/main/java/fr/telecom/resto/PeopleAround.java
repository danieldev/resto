package fr.telecom.resto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Adapter.ProductListAdapter.OnProductSelectedListener;
import fr.telecom.resto.Model.Product;


public class PeopleAround extends Activity implements
        OnProductSelectedListener {

    ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_around);

        final ListView listProduct= (ListView) findViewById(R.id.listProduct);

        ImageView table1=(ImageView)findViewById(R.id.table_one);
        ImageView table2=(ImageView)findViewById(R.id.table_two);

        final TextView title =(TextView) findViewById(R.id.TableNumTitle);

        final View.OnClickListener myHandler = new View.OnClickListener() {
            public void onClick(View v) {

                switch(v.getId()) {
                    case R.id.table_one:
                        adapter=new ProductListAdapter(PeopleAround.this,getProducts(1));
                        title.setText("Table No.1");
                        break;
                    case R.id.table_two:
                        // it was the second button
                        adapter=new ProductListAdapter(PeopleAround.this,getProducts(2));
                        title.setText("Table No.2");
                        break;
                    default:
                        adapter=new ProductListAdapter(PeopleAround.this,new ArrayList());
                }
                listProduct.setAdapter(adapter);
            }
        };

        table1.setOnClickListener(myHandler);
        table2.setOnClickListener(myHandler);

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(adapter.getItem(position));
            }
        });
    }


    private List<Product> getProducts(int tableNum) {
        HashMap<Integer,List<Product>> orders= new HashMap<Integer,List<Product>>();//orders of all tables

        List<Product> order1 = new ArrayList<Product>(); //order of table one
        order1.add(new Product("Soupe à l'oignon", 4.90, "description",
                R.drawable.entree_soupe));
        order1.add(new Product("Coq au vin maison", 14.90, "description",
                R.drawable.main_dish_coq));

        List<Product> order2 = new ArrayList<Product>(); //order of table one
        order2.add(new Product("Oeufs cocotte aux herbes", 8.90,
                "description", R.drawable.entree_oeufs));
        order2.add(new Product("Rôti de boeuf à l'oignon caramélisé", 18.90,
                "description", R.drawable.main_dish_boeuf));

        orders.put(1,order1);
        orders.put(2,order2);

        return orders.get(tableNum);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_people_around, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onProductSelected(Product product) {


    }

    private void selectItem(Product product) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.PRODUCT_TAG, product);
        startActivity(intent);
    }
}
