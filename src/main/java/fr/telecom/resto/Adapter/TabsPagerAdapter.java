package fr.telecom.resto.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.telecom.resto.Fragment.AppetizerFragment;
import fr.telecom.resto.Fragment.DessertFragment;
import fr.telecom.resto.Fragment.DrinksFragment;
import fr.telecom.resto.Fragment.MainDishFragment;
import fr.telecom.resto.MainActivity;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	final int PAGE_COUNT = 4;
	private String tabTitles[] = new String[] { "Entrée", "Plat principal",
			"Dessert", "Boissons" };
    List<String> tagList;
	private Context context;
    FragmentManager fm;

	public TabsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
        this.fm=fm;
		this.context = context;
        tagList=new ArrayList<String>();
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        tagList.add("android:switcher:"+String.valueOf(container.getId())+":"+String.valueOf(getItemId(position)));
        return super.instantiateItem(container, position);
    }

    public void update(String sort_item){
        AppetizerFragment fragment = (AppetizerFragment) this.fm.findFragmentByTag(tagList.get(0));
        if(fragment!=null) {
            fragment.sort(sort_item);
        }
        MainDishFragment fragment_main_dish=(MainDishFragment) this.fm.findFragmentByTag(tagList.get(1));
        if(fragment_main_dish!=null){
            fragment_main_dish.sort(sort_item);
        }
    }

}