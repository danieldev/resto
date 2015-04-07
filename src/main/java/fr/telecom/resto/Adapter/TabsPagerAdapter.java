package fr.telecom.resto.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import fr.telecom.resto.Fragment.AppetizerFragment;
import fr.telecom.resto.Fragment.DessertFragment;
import fr.telecom.resto.Fragment.DrinksFragment;
import fr.telecom.resto.Fragment.MainDishFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	final int PAGE_COUNT = 4;
	private String tabTitles[] = new String[] { "Entrée", "Plat principal",
			"Dessert", "Boissons" };
	private Context context;

	public TabsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment;
		switch (position) {
		case 0:
			fragment = new AppetizerFragment();
			break;
		case 1:
			fragment = new MainDishFragment();
			break;
		case 2:
			fragment = new DessertFragment();
			break;
		case 3:
			fragment = new DrinksFragment();
			break;
		default:
			fragment = new AppetizerFragment();
			break;
		}
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// Generate title based on item position
		return tabTitles[position];
	}
}