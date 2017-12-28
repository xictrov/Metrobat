package v.metrobat.front.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import v.metrobat.front.Fragment.PerdutsFragment;
import v.metrobat.front.Fragment.TrobatsFragment;

/**
 * Created by V on 02/12/2017.
 */

public class sliderAdapter extends FragmentPagerAdapter {

    private final String tabs[]={"Perduts","Trobats"};

    public sliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        android.support.v4.app.Fragment fragment = null;
        if (position == 0) {
            fragment = new PerdutsFragment();
        }
        else if (position == 1) {
            fragment = new TrobatsFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
