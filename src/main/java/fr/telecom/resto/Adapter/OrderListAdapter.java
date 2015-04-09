package fr.telecom.resto.Adapter;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.telecom.resto.R;
import fr.telecom.resto.Model.Product;

public class OrderListAdapter extends ArrayAdapter<Product> {

	private Context context;
	private List<Product> products;
	private LayoutInflater inflater;
	private OnProductRemovedListener callback;

	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

	public OrderListAdapter(Context context, List<Product> products) {
		super(context, R.layout.order_list_item_layout, products);
		this.context = context;
		this.products = products;
		try {
			this.callback = ((OnProductRemovedListener) context);
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
			convertView = inflater.inflate(R.layout.order_list_item_layout,
					parent, false);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.order_name);
			holder.price = (TextView) convertView
					.findViewById(R.id.order_price);
			holder.quantity = (TextView) convertView
					.findViewById(R.id.order_quantity);
			holder.remove = (ImageView) convertView
					.findViewById(R.id.order_remove);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Product product = products.get(position);
		holder.quantity.setText(product.getQuantity() + "x");
		holder.name.setText(product.getName());
		holder.price.setText(currencyFormatter.format(product.getSum()));
		holder.remove.setTag(position);
		holder.remove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int position = (Integer) v.getTag();
				Product productRemoved = products.get(position);
				products.remove(position);
				notifyDataSetChanged();
				callback.onProductRemoved(productRemoved);
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
		TextView quantity;
		TextView name;
		TextView price;
		ImageView remove;
	}

	public interface OnProductRemovedListener {
		public void onProductRemoved(Product product);
	}
}
