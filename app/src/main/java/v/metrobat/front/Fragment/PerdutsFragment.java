package v.metrobat.front.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import v.metrobat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerdutsFragment extends Fragment {


    public PerdutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perduts, container, false);
    }

}
