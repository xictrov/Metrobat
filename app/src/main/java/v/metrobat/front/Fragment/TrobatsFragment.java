package v.metrobat.front.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import back.Item;
import back.ItemData;
import v.metrobat.R;
import v.metrobat.front.Adapter.ItemAdAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrobatsFragment extends Fragment {


    private RecyclerView recyclerTrobats;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item> mlostItems = new ArrayList<>();
    private static ItemData itemData;

    public TrobatsFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemData = new ItemData(getActivity().getApplicationContext());
        itemData.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trobats, container, false);
        recyclerTrobats = (RecyclerView) view.findViewById(R.id.recyclerTrobats);
        recyclerTrobats.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerTrobats.setLayoutManager(mLayoutManager);

        ArrayList<Item> lostItems = itemData.getFoundItems();
        mlostItems.clear();

        for (int i=0; i<lostItems.size(); ++i) {
            Item itemi = new Item();
            itemi.setName(lostItems.get(i).getName());
            itemi.setTransport(lostItems.get(i).getTransport());
            itemi.setTransportLine(lostItems.get(i).getTransportLine());
            itemi.setDate(lostItems.get(i).getDate());
            itemi.setTime(lostItems.get(i).getTime());
            itemi.setDescription(lostItems.get(i).getDescription());
            mlostItems.add(itemi);
        }

        mAdapter = new ItemAdAdapter(getActivity(),mlostItems,itemData);
        recyclerTrobats.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        itemData.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        itemData.close();
        super.onPause();
    }
}
