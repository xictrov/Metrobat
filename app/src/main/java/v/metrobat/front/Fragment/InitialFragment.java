package v.metrobat.front.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import v.metrobat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InitialFragment extends Fragment {

    private Button btnPerdut;
    private Button btnTrobat;

    public InitialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_initial, container, false);

        btnPerdut = (Button) view.findViewById(R.id.btnPerdut);
        btnTrobat = (Button) view.findViewById(R.id.btnTrobat);

        btnPerdut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                NewItemFragment fragment = NewItemFragment.newInstance("lost");
                ft.replace(R.id.content_navigation,fragment);
                ft.addToBackStack(null).commit();
            }
        });

        btnTrobat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                NewItemFragment fragment = NewItemFragment.newInstance("found");
                ft.replace(R.id.content_navigation,fragment);
                ft.addToBackStack(null).commit();
            }
        });

        return view;
    }

}
