package fr.telecom.resto.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.telecom.resto.R;
import fr.telecom.resto.Model.Product;

public class RatingListAdapter extends ArrayAdapter<Product> {

	private Context context;
	private List<Product> products;
	private LayoutInflater inflater;

	public RatingListAdapter(Context context, List<Product> products) {
		super(context, R.layout.rating_list_item_layout, products);
		this.context = context;
		this.products = products;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.rating_list_item_layout,
					parent, false);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.rating_list_name);
			holder.rating = (RatingBar) convertView
					.findViewById(R.id.rating_list_rating_bar);
			holder.image = (ImageView) convertView
					.findViewById(R.id.rating_list_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Product product = products.get(position);
		holder.name.setText(product.getName());
		holder.rating.setRating(product.getRating());

		Picasso.with(context).load(product.getImage()).centerCrop().fit()
				.into(holder.image);

		return convertView;
	}

	/**
	 * 
	 * Holder pattern to increase performance
	 * 
	 */
	private class ViewHolder {
		TextView name;
		RatingBar rating;
		ImageView image;
	}
}
