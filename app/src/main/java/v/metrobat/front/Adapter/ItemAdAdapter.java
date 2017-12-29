package v.metrobat.front.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import back.Item;
import back.ItemData;
import v.metrobat.R;


public class ItemAdAdapter extends RecyclerView.Adapter<ItemAdAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Item> items = new ArrayList<>();
    private ItemData itemData;

    public ItemAdAdapter(Context context, ArrayList<Item> itemList, ItemData itemData) {
        inflater = LayoutInflater.from(context);
        this.items = itemList;
        this.context = context;
        this.itemData = itemData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.content_recycler,parent,false);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Toco", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item current = items.get(position);
        holder.itemName.setText(current.getName());
        holder.itemTransport.setText(current.getTransport());
        holder.itemTransportLine.setText(current.getTransportLine());
        holder.itemDate.setText(current.getDate());
        holder.itemTime.setText(current.getTime());
        holder.itemDescription.setText(current.getDescription());
        //Paràmetres originalment ocults
        //holder.itemDescription.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemTransport, itemTransportLine;
        TextView itemDate, itemTime;
        TextView itemDescription;

        MyViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.tipusItemTextView);
            itemTransport = (TextView) itemView.findViewById(R.id.transportTextView);
            itemTransportLine = (TextView) itemView.findViewById(R.id.transportLineTextView);
            itemDate = (TextView) itemView.findViewById(R.id.dateTextView);
            itemTime = (TextView) itemView.findViewById(R.id.timeTextView);
            itemDescription = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }

}
