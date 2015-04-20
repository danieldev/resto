package fr.telecom.resto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import fr.telecom.resto.Adapter.ProductListAdapter;
import fr.telecom.resto.Adapter.ProductListAdapter.OnProductSelectedListener;
import fr.telecom.resto.Model.Product;

public class DetailActivity extends Activity implements OnProductSelectedListener {

	public static final String PRODUCT_TAG = "fr.telecom.resto.DetailActivity.PRODUCT";

	private Product product;
    ListView list;
    ArrayList<String> c=new ArrayList<String>();
    ListView product_accompany;
    ProductListAdapter adapter;

    ArrayList<Product> addProducts=new ArrayList<Product>(); //products added

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

        //set list view of comments
        list = (ListView) findViewById(R.id.listComments);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,product.getComments());
        list.setAdapter(adapter1);

        //set list view that contains product accompany
        product_accompany= (ListView) findViewById(R.id.listProductAccompany);
        adapter=new ProductListAdapter(this,getAccompany());
        product_accompany.setAdapter(adapter);

        //when item is clicked, turn to DetailActivity
        product_accompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(adapter.getItem(position));
            }
        });

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

    private List<Product> getAccompany() {
        List<Product> product = new ArrayList<Product>();

        Product p = new Product("Misto di verdure", 9.90, "Roquette, artichaut, tomates cerise marinées",
                R.drawable.misto_di_verdure);
        p.setRating(3);
        p.setCalorie(200);
        p.getComments().add("Délicieux !");
        p.getComments().add("Excellent");
        p.getComments().add("Bof");

        product.add(p);


        p = new Product("Tiramisú primavera", 10.90, "Tiramisú salé au grana padano, poivrons marinés, crème de baslamique",
                R.drawable.tiramisu_primavera);
        p.setRating(4);
        p.setCalorie(300);
        p.getComments().add("Excellent, meilleur que le tiramisu sucré !");
        p.getComments().add("Je n'ai pas trop aimé");

        product.add(p);

        return product;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

    //add clicked
    @Override
    public void onProductSelected(final Product product) {
        new AlertDialog.Builder(this)
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


    //item selected, turn to DetailActivity
    private void selectItem(Product product) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.PRODUCT_TAG, product);
        startActivityForResult(intent,1);
    }

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
