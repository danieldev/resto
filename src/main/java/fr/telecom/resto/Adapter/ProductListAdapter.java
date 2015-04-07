package fr.telecom.resto.Adapter;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.telecom.resto.R;
import fr.telecom.resto.Model.Product;

public class ProductListAdapter extends ArrayAdapter<Product> {

	private Context context;
	private List<Product> products;
	private LayoutInflater inflater;
	private OnProductSelectedListener callback;
	
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

	public ProductListAdapter(Context context, List<Product> products) {
		super(context, R.layout.product_list_item_layout, products);
		this.context = context;
		this.products = products;
		try {
			this.callback = ((OnProductSelectedListener) context);
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Fragment must implement AdapterCallback.");
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.product_list_item_layout,
					parent, false);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.product_name);
			holder.price = (TextView) convertView
					.findViewById(R.id.product_price);
			holder.image = (ImageView) convertView
					.findViewById(R.id.product_image);
			holder.add = (Button) convertView.findViewById(R.id.product_add);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Product product = products.get(position);
		holder.name.setText(product.getName());
		holder.price.setText(currencyFormatter.format(product.getPrice()));
		holder.image.setBackgroundResource(R.drawable.entree1);
		holder.add.setTag(position);
		holder.add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int position = (Integer) v.getTag();
				callback.onProductSelected(products.get(position));
			}
		});
		return convertView;
	}

	/**
	 * 
	 * Holder pattern to increase performance
	 * 
	 */
	private class ViewHolder {
		TextView name;
		TextView price;
		ImageView image;
		Button add;
	}

	public interface OnProductSelectedListener {
		public void onProductSelected(Product product);
	}

}
