package v.metrobat.front.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import v.metrobat.R;
import v.metrobat.front.Adapter.sliderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ObjectsAdsListFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private OnFragmentInteractionListener listener;
    private sliderAdapter sliderAdapter;
    ArrayList<String> tabName;

    private static final String ARG_ITEMADLIST = "itemadlistframgent";

    public ObjectsAdsListFragment() {
        // Required empty public constructor
    }

    public static ObjectsAdsListFragment newInstance(String navigation) {
        ObjectsAdsListFragment fragment = new ObjectsAdsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEMADLIST, navigation);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);

        sliderAdapter sA = new sliderAdapter(getChildFragmentManager());
        viewPager.setAdapter(sA);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                getChildFragmentManager().beginTransaction().addToBackStack(null).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Note that we are passing childFragmentManager, not FragmentManager
        sliderAdapter = new sliderAdapter(getChildFragmentManager());
        viewPager.setAdapter(sliderAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
