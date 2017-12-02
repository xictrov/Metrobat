package v.metrobat.front;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import v.metrobat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    View view;
    ViewPager viewpager;
    TabLayout tabbedLayout;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);

        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        viewpager.setAdapter(new sliderAdapter(getChildFragmentManager()));
        tabbedLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabbedLayout.post(new Runnable() {
            @Override
            public void run() {
                tabbedLayout.setupWithViewPager(viewpager);
            }
        });

        return view;
    }
}
