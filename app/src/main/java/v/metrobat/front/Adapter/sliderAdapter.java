package v.metrobat.front.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import v.metrobat.front.Fragment.contentFragment;

/**
 * Created by V on 02/12/2017.
 */

public class sliderAdapter extends FragmentPagerAdapter {

    private final String tabs[]={"Perduts","Trobats"};

    public sliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new contentFragment();
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
