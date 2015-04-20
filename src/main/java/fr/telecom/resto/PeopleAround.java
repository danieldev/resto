package fr.telecom.resto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    public static final String TAG = "fr.telecom.resto.PeopleAround.tag";
    ProductListAdapter adapter;
    ArrayList<Product> addProducts=new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_around);

        final ListView listProduct= (ListView) findViewById(R.id.listProduct);

        ImageView table1=(ImageView)findViewById(R.id.table_one);
        ImageView table2=(ImageView)findViewById(R.id.table_two);
        ImageView table3=(ImageView)findViewById(R.id.table_three);

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
                    case R.id.table_three:
                        // it was the third button
                        adapter=new ProductListAdapter(PeopleAround.this,getProducts(3));
                        title.setText("Table No.3");
                        break;
                    default:
                        adapter=new ProductListAdapter(PeopleAround.this,new ArrayList());
                }
                listProduct.setAdapter(adapter);
            }
        };

        table1.setOnClickListener(myHandler);
        table2.setOnClickListener(myHandler);
        table3.setOnClickListener(myHandler);

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(adapter.getItem(position));
            }
        });

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private List<Product> getProducts(int tableNum) {
        HashMap<Integer,List<Product>> orders= new HashMap<Integer,List<Product>>();//orders of all tables

        List<Product> order1 = new ArrayList<Product>(); //order of table one
        Product p = new Product("Misto di verdure", 9.90, "Roquette, artichaut, tomates cerise marinées",
                R.drawable.misto_di_verdure);
        p.setRating(3);
        p.setCalorie(200);
        p.getComments().add("Délicieux !");
        p.getComments().add("Excellent");
        p.getComments().add("Bof");

        order1.add(p);

        Product p4 = new Product("Pizza Bolognese", 11, "tomate, viande hachée, olives, gruyère", R.drawable.pizza_bolognese);
        p4.setRating(3);
        p4.setCalorie(430);
        p4.getComments().add("La pâte était trop cuite...");
        p4.getComments().add("Délicieux !");

        order1.add(p4);


        List<Product> order2 = new ArrayList<Product>(); //order of table one

        p = new Product("Tiramisú primavera", 10.90, "Tiramisú salé au grana padano, poivrons marinés, crème de baslamique",
                R.drawable.tiramisu_primavera);
        p.setRating(4);
        p.setCalorie(300);
        p.getComments().add("Excellent, meilleur que le tiramisu sucré !");
        p.getComments().add("Je n'ai pas trop aimé");

        order2.add(p);

        Product p6 = new Product("Spaghetti Carbonara", 11, "Spaghetti avec jambon creme et fromage", R.drawable.spaghetti_carbonara);
        p6.setRating(3);
        p6.setCalorie(550);
        p6.getComments().add("Très bon mais trop gras...");
        p6.getComments().add("Délicieux !");

        order2.add(p6);

        List<Product> order3 = new ArrayList<Product>(); //order of table one

        Product p7 = new Product("Spaghetti Bolognese", 11, "Spaghetti avec sauce tomate et viande hachée",
                R.drawable.spaghetti_bolognese);
        p7.setRating(4);
        p7.setCalorie(400);
        p7.getComments().add("Excellentissime !");
        p7.getComments().add("Délicieux !");

        order3.add(p7);

        p6 = new Product("Spaghetti Carbonara", 11, "Spaghetti avec jambon creme et fromage", R.drawable.spaghetti_carbonara);
        p6.setRating(3);
        p6.setCalorie(550);
        p6.getComments().add("Très bon mais trop gras...");
        p6.getComments().add("Délicieux !");

        order3.add(p6);

        orders.put(1,order1);
        orders.put(2,order2);
        orders.put(3,order3);

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
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onProductSelected(final Product product) {

                new AlertDialog.Builder(PeopleAround.this)
                        .setTitle(R.string.order_dialog_title)
                        .setMessage(getString(R.string.confirm_add_product_text) + " " + product.getName() + " ?\n")
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // add product
                                        addProducts.add(product);
                                    }
                                })
                        .setNegativeButton(android.R.string.no,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // do nothing
                                    }
                                }).setIcon(android.R.drawable.ic_dialog_info)
                        .show();


    }

    private void selectItem(Product product) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.PRODUCT_TAG, product);
        startActivityForResult(intent,1);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("add", addProducts);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ArrayList<Product> add = data.getParcelableArrayListExtra("add");
                for(int i=0;i<add.size();i++){
                    addProducts.add(add.get(i));
                }
            }
        }
    }
}
